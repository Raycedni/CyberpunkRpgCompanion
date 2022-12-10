package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants

import CyberpunkCharacter
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.annotations.ReturnsSpecificImplant
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.AttributesEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges.TypesOfTemporaryPointChangeEnum
import idgen
import java.lang.reflect.Method
import java.util.UUID
import kotlin.reflect.jvm.javaMethod

class ImplantFactory private constructor() {
    private val supportedImplantMap = mutableMapOf<Double, Method>()

    init {
        this.javaClass.declaredMethods.forEach {
            if (it.annotations.contains(ReturnsSpecificImplant()))
                supportedImplantMap[idgen()] = it
        }
    }

    private fun implantBase(implantName: String = "", monetaryValue: Double = 0.0, itemClassId: Double = 0.0): Implant {
        return Implant(itemClassId, UUID.randomUUID(), implantName, monetaryValue)
    }

    fun buildImplantFromClassId(id: Double): Implant = supportedImplantMap.get(id)?.invoke(this) as Implant

    @ReturnsSpecificImplant
    fun buildSandevistan(): Implant {
        val sandy = implantBase(
            implantName = "Sandy",
            monetaryValue = 5.5,
            itemClassId = supportedImplantMap.filterValues { it.equals(ImplantFactory::buildSandevistan.javaMethod) }.keys.first()
        )

        sandy.addEffectOnCharacter { character ->
            if (character is CyberpunkCharacter) {
                character.addTemporaryAttributePointFluctiation(
                    attribute = AttributesEnum.Reflexes.name,
                    skillPointFluctiation = 10,
                    condition = { character.getInstalledImplants().contains(sandy) },
                    causedBy = sandy,
                    temporaryPointChangeType = TypesOfTemporaryPointChangeEnum.POSITIVE
                )
            }
        }

        return sandy
    }

    companion object {
        private val instance = create()
        private fun create() = ImplantFactory()

        fun getFactory() = instance
    }
}
