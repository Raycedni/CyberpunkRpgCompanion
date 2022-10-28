package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.IntelligenceSkillListEnum

class Intelligence(override var points: Int) :
    AttributeBaseClass<IntelligenceSkillListEnum>("Intelligence", points, enumValues())