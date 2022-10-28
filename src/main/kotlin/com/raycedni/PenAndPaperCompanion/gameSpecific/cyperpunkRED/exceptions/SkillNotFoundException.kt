package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.exceptions

class SkillNotFoundException(val skillName: String, val attributeName: String) : RuntimeException() {
    override val message: String?
        get() {
            return "Skill $skillName was not found in Attribute $attributeName"
        }
}