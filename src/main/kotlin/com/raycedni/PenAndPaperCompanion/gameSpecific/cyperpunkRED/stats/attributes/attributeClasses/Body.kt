package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.AttributeBaseClass
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.BodySkillListEnum

class Body(override var points: Double) : AttributeBaseClass<BodySkillListEnum>("Body", points, enumValues())