import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.Attribute
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.BodySkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.CoolSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.EmpathySkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.IntelligenceSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.ReflexesSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.TechSkillListEnum
import kotlin.math.max

class CyberpunkCharacter(
    override var name: String,
    override var health: Double,
    private var humanity: Double,
    private val attributes: MutableMap<String, Attribute>

) : Character {
    override var inventory: MutableMap<String, Item> = mutableMapOf()

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
        )
    )

    override fun addItemToInventory(item: Item) {
        TODO("Not yet implemented")
    }

    override fun removeItemFromInventory() {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (other is CyberpunkCharacter)
            return (this.name.equals(other.name) &&
                    this.health.equals(other.health) &&
                    this.humanity.equals(other.humanity) &&
                    this.attributes.equals(other.attributes))
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

    fun getAttributes(): Map<String, Attribute> {
        return attributes.toMap()
    }

    fun setSkillPointsTo(attributeOfSkill: String, skillName: String, newSkillPoints: Int): CyberpunkCharacter {
//        TODO
//        attributes[attributeOfSkill]?.skillsInAttribute?.get(skillName)?.basePoints = newSkillPoints
        return this
    }

    fun increaseSkillPointsBy(attributeOfSkill: String, skillName: String, newSkillPoints: Int): CyberpunkCharacter {
        if (newSkillPoints > 0) {
//            TODO
//            attributes[attributeOfSkill]?.skillsInAttribute?.get(skillName)?.basePoints?.plus(newSkillPoints)
            return this
        } else
            throw IllegalArgumentException()
    }

    fun addNewAttribute(attributeName: String, attributeSkillList: List<String>): CyberpunkCharacter {
        attributes.putIfAbsent(attributeName, defaultAttributeEntry(attributeName, attributeSkillList))
        return this
    }
}

private inline fun <reified T : Enum<T>> getEnumEntriesAsList(): List<String> {
    return enumValues<T>().joinToString { it.name }.split(", ")
}

private inline fun <reified T : Enum<T>> defaultAttributeEntry(): Pair<String, Attribute> {
    val attributeName = T::class.simpleName.toString().substringBefore("SkillList")

    return attributeName to defaultAttributeEntry(attributeName, getEnumEntriesAsList<T>())
}

private fun defaultAttributeEntry(attributeName: String, attributeSkillList: List<String>): Attribute {
    return Attribute(attributeName, 0, attributeSkillList)
}