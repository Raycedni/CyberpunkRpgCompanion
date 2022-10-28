package com.raycedni.PenAndPaperCompanion.general.stats

interface DamageTypeBase {
    val name: String

    fun damageAgainstResistance(resistanceTypeBase: ResistanceTypeBase): Double
}