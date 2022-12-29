import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.Implant
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.ImplantFactory
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValue
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.Attribute
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.BodySkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.CoolSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.EmpathySkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.IntelligenceSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.ReflexesSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.TechSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges.TemporaryEffectSource
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges.TemporaryPointChange
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges.TypesOfTemporaryPointChangeEnum
import com.raycedni.PenAndPaperCompanion.general.characters.Inventory
import kotlin.math.max

class CyberpunkCharacter(
    var name: String,
    var health: Double,
    private var humanity: Double,
    private val attributes: MutableMap<String, Attribute>,
    private val installedImplants: MutableList<Implant> = mutableListOf()
) {
    private var inventory = Inventory()
    private var stringMapOfAllSkills = mutableMapOf<String, String>()
    private var isStringMapOfAllSkillsAccurate = false

    constructor(name: String, health: Double, humanity: Double) : this(
        name,
        health,
        humanity,
        mutableMapOf(
            defaultAttributeEntry<BodySkillListEnum>(),
            defaultAttributeEntry<CoolSkillListEnum>(),
            defaultAttributeEntry<EmpathySkillListEnum>(),
            defaultAttributeEntry<IntelligenceSkillListEnum>(),
            defaultAttributeEntry<ReflexesSkillListEnum>(),
            defaultAttributeEntry<TechSkillListEnum>()
        ),
        mutableListOf<Implant>()
    )

    constructor(name: String, health: Double, humanity: Double, installedImplantIds: List<Double>) : this(
        name,
        health,
        humanity
    ) {
        installedImplantIds.forEach { ImplantFactory.getFactory().buildImplantFromClassId(it) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is CyberpunkCharacter)
            return (this.name.equals(other.name) &&
                    this.health.equals(other.health) &&
                    this.humanity.equals(other.humanity) &&
                    this.attributes.equals(other.attributes) &&
                    this.installedImplants.equals(other.installedImplants))
        else
            return super.equals(other)
    }

    fun getHumanity() = humanity

    fun removeHumanity(amount: Double) {
        if (amount > 0)
            humanity = max(humanity - amount, 0.0)
        else
            throw IllegalArgumentException()
    }

    fun getAttributes() = attributes.toMap()

    fun getInstalledImplants() = installedImplants.toList()

//    fun getInstalledImplantIds() = installedImplants.toDoubleList();

    fun setSkillPointsTo(attributeOfSkill: String, skillName: String, newSkillPoints: Int): CyberpunkCharacter {
        attributes[attributeOfSkill]?.getSkills()?.get(skillName)?.setPointsTo(newSkillPoints)
        return this
    }

    fun increaseSkillPointsBy(attributeOfSkill: String, skillName: String, newSkillPoints: Int): CyberpunkCharacter {
        if (newSkillPoints > 0) {
            attributes[attributeOfSkill]?.attributeSkillMap?.get(skillName)?.affectBasePointsBy(newSkillPoints)
            return this
        } else
            throw IllegalArgumentException()
    }

    fun addTemporaryAttributePointFluctiation(
        attribute: String,
        skillPointFluctiation: Int,
        temporaryPointChangeType: TypesOfTemporaryPointChangeEnum,
        condition: () -> Boolean,
        causedBy: TemporaryEffectSource<*>
    ) {
        attributes[attribute]?.temporaryPointHandler?.addTemporaryEffect(
            TemporaryPointChange(
                skillPointFluctiation,
                temporaryPointChangeType,
                condition,
                causedBy
            )
        )
    }

    fun addTemporarySkillPointFluctuation(
        skillName: String,
        skillPointFluctiation: Int,
        temporaryPointChangeType: TypesOfTemporaryPointChangeEnum,
        condition: () -> Boolean,
        causedBy: TemporaryEffectSource<*>
    ) {
        attributes[getAttributeOfSkill(skillName)]?.attributeSkillMap?.get(skillName)
            ?.temporaryPointHandler?.addTemporaryEffect(
                TemporaryPointChange(
                    skillPointFluctiation,
                    temporaryPointChangeType,
                    condition,
                    causedBy
                )
            )
    }

    private fun getAttributeOfSkill(skillName: String): String {
        if (!isStringMapOfAllSkillsAccurate)
            refillStringMapOfAllSkills()

        return stringMapOfAllSkills.get(skillName)!!
    }

    private fun refillStringMapOfAllSkills() {
        attributes.forEach { attributeName, attributeValue ->
            attributeValue.attributeSkillMap.forEach { skillName, skillValue ->
                stringMapOfAllSkills.put(
                    skillName,
                    attributeName
                )
            }
        }
        isStringMapOfAllSkillsAccurate = true
    }

    fun installImplant(implant: Implant) {
        installedImplants.add(implant)
        implant.getListOfEffectsOnCharacter().forEach { it.invoke(this) }
    }

    fun addNewAttribute(attributeName: String, attributeSkillList: List<String>): CyberpunkCharacter {
        attributes.putIfAbsent(attributeName, defaultAttributeEntry(attributeName, attributeSkillList))
        isStringMapOfAllSkillsAccurate = false
        return this
    }

    fun addNewSkillToAttribute(attributeName: String, skillName: String, skillValue: SkillValue): CyberpunkCharacter {
        attributes[attributeName]?.attributeSkillMap?.putIfAbsent(skillName, skillValue)
        isStringMapOfAllSkillsAccurate = false
        return this
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + health.hashCode()
        result = 31 * result + humanity.hashCode()
        result = 31 * result + attributes.hashCode()
        result = 31 * result + installedImplants.hashCode()
        result = 31 * result + inventory.hashCode()
        result = 31 * result + stringMapOfAllSkills.hashCode()
        return result
    }
}

private inline fun <reified T : Enum<T>> getEnumEntriesAsList(): List<String> {
    return enumValues<T>().joinToString { it.name }.split(", ")
}

private inline fun <reified T : Enum<T>> defaultAttributeEntry(): Pair<String, Attribute> {
    val attributeName = T::class.simpleName.toString().substringBefore("SkillList")

    return attributeName to defaultAttributeEntry(attributeName, getEnumEntriesAsList<T>())
}

private fun List<Implant>.toDoubleList(): List<Double> {
    val idList = mutableListOf<Double>()

    this.forEach { idList.add(it.itemClassId) }

    return idList.toList()
}

private fun defaultAttributeEntry(attributeName: String, attributeSkillList: List<String>): Attribute {
    return Attribute(attributeName, 0, attributeSkillList)
}