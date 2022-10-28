package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.SkillValues
import com.raycedni.PenAndPaperCompanion.general.stats.BaseStat

class Intelligence(override var points: Double, val skills: IntelligenceSkills) : BaseStat {
    override val name: String = "Intelligence"

    data class IntelligenceSkills(
        val anthropology: SkillValues = SkillValues(),
        val awarenessNotice: SkillValues = SkillValues(),
        val biology: SkillValues = SkillValues(),
        val botany: SkillValues = SkillValues(),
        val chemistry: SkillValues = SkillValues(),
        val composition: SkillValues = SkillValues(),
        val diagnoseIllness: SkillValues = SkillValues(),
        val educationGenKnow: SkillValues = SkillValues(),
        val expert: SkillValues = SkillValues(),
        val gamble: SkillValues = SkillValues(),
        val accounting: SkillValues = SkillValues(),
        val geology: SkillValues = SkillValues(),
        val hide: SkillValues = SkillValues(),
        val history: SkillValues = SkillValues(),
        val language: SkillValues = SkillValues(),
        val librarySearch: SkillValues = SkillValues(),
        val mathematics: SkillValues = SkillValues(),
        val physics: SkillValues = SkillValues(),
        val programming: SkillValues = SkillValues(),
        val shadowTrack: SkillValues = SkillValues(),
        val stockMarket: SkillValues = SkillValues(),
        val systemKnowledge: SkillValues = SkillValues(),
        val teaching: SkillValues = SkillValues(),
        val wildernessSurvival: SkillValues = SkillValues(),
        val zoology: SkillValues = SkillValues()
    )
}