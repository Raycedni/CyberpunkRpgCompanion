package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.exceptions.SkillNotFoundException
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValues
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

open class AttributeBaseClass<T : Enum<T>>(
    override val name: String,
    override var points: Double,
    skillListEnumValues: Array<T>
) : BaseStat {
    protected val skillMap = mutableMapOf<T, SkillValues>()

    init {
        skillListEnumValues.forEach { skillMap.putIfAbsent(it, SkillValues()) }
    }

    fun getSkill(skill: T): SkillValues? {
        return skillMap.get(skill)
    }

    fun updateSkill(skill: T, skillValue: SkillValues) {
        if (skillMap.containsKey(skill))
            skillMap[skill] = skillValue
        else
            throw SkillNotFoundException(skill.name, name)
    }


    fun getSkillMapCopy(): Map<T, SkillValues> {
        return skillMap.toMap()
    }

    private fun <T : Enum<T>> iterator(values: () -> Array<T>): Iterator<T> = values()
        .asIterable()
        .iterator()
}