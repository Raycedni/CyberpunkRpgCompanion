package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants

import Item
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.serializers.ImplantDeserializer
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges.TemporaryEffectSource
import java.util.UUID

@JsonDeserialize(using = ImplantDeserializer::class)
class Implant(
    itemClassId: Double,
    objectId: UUID,
    name: String,
    monetaryValue: Double,
    @JsonIgnore
    private val effectsOnCharacter: MutableList<(effect: Any) -> Unit> = mutableListOf()
) : Item(itemClassId, name, monetaryValue, objectId), TemporaryEffectSource<Implant> {
    @JsonIgnore
    fun getListOfEffectsOnCharacter(): List<(effect: Any) -> Unit> = effectsOnCharacter.toList()

    fun addEffectOnCharacter(effect: (effect: Any) -> Unit): Implant {
        effectsOnCharacter.add(effect)
        return this
    }

    fun addEffectOnCharacter(effectList: List<(effect: Any) -> Unit>): Implant {
        effectList.forEach { addEffectOnCharacter(it) }
        return this
    }

    override fun generateSourceDescription(): String {
        TODO("Not yet implemented")
    }

    override fun getSourceClass(): Class<Implant> {
        return this.javaClass
    }

    override fun getSourceObject(): Implant {
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Implant

        return other.name.equals(name) &&
                other.itemClassId.equals(itemClassId) &&
//                other.objectId.equals(objectId) &&
                other.monetaryValue.equals(monetaryValue)
    }

    override fun hashCode(): Int {
        return effectsOnCharacter.hashCode()
    }
}