package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.exceptions.SkillNotFoundException
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValue
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

open class Attribute(
    override val name: String,
    override var points: Int,
    attributeSkillList: List<String>
) : BaseStat {
    val skillsInAttribute = mutableMapOf<String, SkillValue>()

    init {
        attributeSkillList.forEach { skillsInAttribute.putIfAbsent(it, SkillValue()) }
    }

    fun getSkill(skill: String): SkillValue? {
        if (skillsInAttribute.containsKey(skill))
            return skillsInAttribute[skill]
        else
            throw SkillNotFoundException(skill, name)
    }

    fun updateSkill(skill: String, skillValue: SkillValue) {
        if (skillsInAttribute.containsKey(skill))
            skillsInAttribute[skill] = skillValue
        else
            throw SkillNotFoundException(skill, name)
    }

    fun updateMultipleSkills(skillMap: MutableMap<String, SkillValue>) {
        skillMap.forEach { (skill, skillvalue) -> updateSkill(skill, skillvalue) }
    }


    fun getSkills(): Map<String, SkillValue> {
        return skillsInAttribute.toMap()
    }
}