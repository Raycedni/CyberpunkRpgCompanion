package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValues
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

class Empathy(override val points: Double, skills: EmpathySkills) :BaseStat {
    override val name: String = "Empathy"

    data class EmpathySkills(
        val humanPerception: SkillValues = SkillValues(),
        val interview: SkillValues = SkillValues(),
        val leadership: SkillValues = SkillValues(),
        val seduction: SkillValues = SkillValues(),
        val social: SkillValues = SkillValues(),
        val persuation: SkillValues = SkillValues(),
        val fastTalk: SkillValues = SkillValues(),
        val perform: SkillValues = SkillValues()
    )
}