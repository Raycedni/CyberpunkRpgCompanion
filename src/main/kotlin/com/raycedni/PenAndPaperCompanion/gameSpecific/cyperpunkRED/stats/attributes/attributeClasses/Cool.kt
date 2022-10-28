package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValues
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

class Cool(override val points: Double, val skills: CoolSkills) :BaseStat {
    override val name: String = "Cool"

    data class CoolSkills(
        val interrogation: SkillValues = SkillValues(),
        val intimidate: SkillValues = SkillValues(),
        val oratory: SkillValues = SkillValues(),
        val resistTorture: SkillValues = SkillValues(),
        val resistDrugs: SkillValues = SkillValues(),
        val streetwise: SkillValues = SkillValues()
    )
}