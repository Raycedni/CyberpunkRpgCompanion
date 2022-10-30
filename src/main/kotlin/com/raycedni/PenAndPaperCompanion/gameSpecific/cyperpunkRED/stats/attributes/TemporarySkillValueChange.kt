package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

class TemporaryPointChange(val valueChange: Int, private val condition: () -> Boolean, val causedBy: Any) {
    fun isStillValid(): Boolean = condition.invoke()
}