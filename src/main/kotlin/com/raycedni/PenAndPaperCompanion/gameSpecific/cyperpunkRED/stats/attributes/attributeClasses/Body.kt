package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

class Body(override val points: Double, val skills: BodySkills) :BaseStat {
    override val name: String = "Body"

    data class BodySkills(
        val endurance:SkillValues = SkillValues(),
        val strengthFeat:SkillValues = SkillValues(),
        val swimming:SkillValues = SkillValues()
    )
}