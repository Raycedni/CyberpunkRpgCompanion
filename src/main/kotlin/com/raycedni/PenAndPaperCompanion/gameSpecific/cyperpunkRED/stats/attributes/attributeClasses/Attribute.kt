package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.fasterxml.jackson.annotation.JsonIgnore
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.exceptions.SkillNotFoundException
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValue
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges.TemporaryPointHandler
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

open class Attribute(
    override val name: String,
    override var basePoints: Int,
    val attributeSkillMap: MutableMap<String, SkillValue>
) : BaseStat {
    val temporaryPointHandler = TemporaryPointHandler()

    constructor(name: String, points: Int, attributeSkillList: List<String>) : this(
        name,
        points,
        attributeSkillList.toAttributeSkillMap()
    )

    @JsonIgnore
    var currentPoints: Int = 0
        get() {
            return basePoints + temporaryPointHandler.getSumOfAllEffects()
        }

    override fun equals(other: Any?): Boolean {
        if (other is Attribute)
            return (this.name.equals(other.name) &&
                    this.basePoints.equals(other.basePoints) &&
                    this.attributeSkillMap.equals(other.attributeSkillMap))
        else
            return super.equals(other)
    }

    fun getSkill(skill: String): SkillValue? {
        if (attributeSkillMap.containsKey(skill))
            return attributeSkillMap[skill]
        else
            throw SkillNotFoundException(skill, name)
    }

    fun updateSkill(skill: String, skillValue: SkillValue) {
        if (attributeSkillMap.containsKey(skill))
            attributeSkillMap[skill] = skillValue
        else
            throw SkillNotFoundException(skill, name)
    }

    fun updateMultipleSkills(skillMap: MutableMap<String, SkillValue>) {
        skillMap.forEach { (skill, skillvalue) -> updateSkill(skill, skillvalue) }
    }


    fun getSkills(): Map<String, SkillValue> {
        return attributeSkillMap.toMap()
    }
}

private fun List<String>.toAttributeSkillMap(): MutableMap<String, SkillValue> {
    val map = mutableMapOf<String, SkillValue>()
    this.forEach { map.putIfAbsent(it, SkillValue()) }

    return map
}