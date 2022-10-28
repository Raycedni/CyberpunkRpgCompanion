package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValues
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

class Tech(override val points: Double, skills: TechSkills) :BaseStat {
    override val name: String = "Tech"

    data class TechSkills(
        val aeroTech: SkillValues = SkillValues(),
        val avTech: SkillValues = SkillValues(),
        val basicTech: SkillValues = SkillValues(),
        val cryotankOperation: SkillValues = SkillValues(),
        val cyberdeckDesign: SkillValues = SkillValues(),
        val cyberTech: SkillValues = SkillValues(),
        val demolitions: SkillValues = SkillValues(),
        val disguise: SkillValues = SkillValues(),
        val electronics: SkillValues = SkillValues(),
        val electricalSecurity: SkillValues = SkillValues(),
        val firstAid: SkillValues = SkillValues(),
        val forgery: SkillValues = SkillValues(),
        val gyroTech: SkillValues = SkillValues(),
        val paintOrDraw: SkillValues = SkillValues(),
        val photoAndFilm: SkillValues = SkillValues(),
        val pharmacuticals: SkillValues = SkillValues(),
        val pickLock: SkillValues = SkillValues(),
        val pickPocket: SkillValues = SkillValues(),
        val playInstrument: SkillValues = SkillValues(),
        val weaponsmith: SkillValues = SkillValues()
    )
}