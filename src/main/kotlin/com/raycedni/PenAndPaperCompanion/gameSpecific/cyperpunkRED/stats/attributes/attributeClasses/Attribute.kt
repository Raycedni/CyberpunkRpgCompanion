package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.exceptions.SkillNotFoundException
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValue
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

open class Attribute(
    override val name: String,
    override var points: Int,
    val attributeSkillMap: MutableMap<String, SkillValue>
) : BaseStat {
    constructor(name: String, points: Int, attributeSkillList: List<String>) : this(
        name,
        points,
        attributeSkillList.toAttributeSkillMap()
    )

    override fun equals(other: Any?): Boolean {
        if (other is Attribute)
            return (this.name.equals(other.name) &&
                    this.points.equals(other.points) &&
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