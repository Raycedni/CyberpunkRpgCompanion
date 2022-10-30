package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

class AttributeSkillList(private val skillList: MutableMap<String, SkillValue>) {
    fun getAllSkillInformationByName(skillName: String): SkillValue? = skillList[skillName]
}