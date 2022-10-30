package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

import com.fasterxml.jackson.annotation.JsonIgnore
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.Implant
import java.util.Optional


class SkillValue(private var basePoints: Int = 0, private var skillChip: Optional<Implant> = Optional.empty()) {
    protected var temporaryEffects: MutableList<TemporaryPointChange> = mutableListOf()

    fun getSkillChip() = skillChip
    fun getBasePoints() = basePoints

    fun installSkillChip(implant: Implant) {
        skillChip = Optional.of(implant)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SkillValue)
            return (this.basePoints.equals(other.basePoints) &&
                    this.skillChip.equals(other.getSkillChip()) &&
                    this.temporaryEffects.equals(other.temporaryEffects)
                    )
        else
            return super.equals(other)
    }

    @JsonIgnore
    fun getPoints(): Int {
        return basePoints - getSumOfAllTempEffects()
    }

    private fun getSumOfAllTempEffects(): Int {
        var sumOfAllEffects = 0
        temporaryEffects.forEach {
            if (it.isStillValid())
                sumOfAllEffects.plus(it.valueChange)
            else
                temporaryEffects.remove(it)
        }
        return temporaryEffects.filter { it.isStillValid() }.toListOfInt().sum()
    }

    private fun checkForAndRemoveInvalidEffects(): MutableList<TemporaryPointChange> {
//        TODO
//        temporaryEffects.forEach{if(!it.isStillValid()) temporaryEffects.remove(it)}
        return mutableListOf()
    }

    private fun Iterable<TemporaryPointChange>.toListOfTemporaryEffects() = this.toList()

    private fun Iterable<TemporaryPointChange>.toListOfInt(): List<Int> {
        val listOfPoints = mutableListOf<Int>()
        this.forEach { listOfPoints.add(it.valueChange) }
        return listOfPoints
    }

    fun getallTemporaryEffects() = temporaryEffects.toList()
    fun cleanseAllTemporaryEffects() = temporaryEffects.removeAll { true }
    fun cleanseAllNegativeEffects() = temporaryEffects.removeAll { it.valueChange < 0 }
    fun cleanseAllPositiveEffects() = temporaryEffects.removeAll { it.valueChange > 0 }
}