package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValues
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

class Reflexes(override val points: Double, val skills: ReflexSkills) : BaseStat {
    override val name: String = "Reflexes"

    data class ReflexSkills(
        val archery: SkillValues = SkillValues(),
        val athletics: SkillValues = SkillValues(),
        val brawling: SkillValues = SkillValues(),
        val dance: SkillValues = SkillValues(),
        val dodgeEscape: SkillValues = SkillValues(),
        val driving: SkillValues = SkillValues(),
        val fencing: SkillValues = SkillValues(),
        val handgun: SkillValues = SkillValues(),
        val heavyWeapons: SkillValues = SkillValues(),
        val martialArts: SkillValues = SkillValues(),
        val melee: SkillValues = SkillValues(),
        val motorcycle: SkillValues = SkillValues(),
        val operateHEaVYMachinery: SkillValues = SkillValues(),
        val pilot: SkillValues = SkillValues(),
        val rifle: SkillValues = SkillValues(),
        val stealth: SkillValues = SkillValues(),
        val submachineGun: SkillValues = SkillValues()
    )
}