package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

class AttributeSkillList(private val skillList: MutableMap<String, SkillValues>) {
    fun getAllSkillInformationByName(skillName: String): SkillValues? = skillList[skillName]
}