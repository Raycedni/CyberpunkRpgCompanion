package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

import com.fasterxml.jackson.annotation.JsonIgnore
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.Implant
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges.TemporaryPointHandler
import java.util.Optional


class SkillValue(private var basePoints: Int = 0, private var skillChip: Optional<Implant> = Optional.empty()) {
    val temporaryPointHandler = TemporaryPointHandler()

    @JsonIgnore
    protected var currentPoints: Int = 0
        get() {
            return basePoints + temporaryPointHandler.getSumOfAllEffects()
        }

    fun getSkillChip() = skillChip
    fun getBasePoints() = basePoints

    fun installSkillChip(implant: Implant) {
        skillChip = Optional.of(implant)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SkillValue)
            return (this.basePoints.equals(other.basePoints) &&
                    this.skillChip.equals(other.getSkillChip()) &&
                    this.temporaryPointHandler.equals(other.temporaryPointHandler)
                    )
        else
            return super.equals(other)
    }

    fun affectBasePointsBy(pointsWhichWillBeApplied: Int) {
        basePoints += pointsWhichWillBeApplied
    }

    fun setPointsTo(newAmountOfPoints: Int) = if (newAmountOfPoints > 0) basePoints =
        newAmountOfPoints else throw IllegalArgumentException("this method only accepts positive values")
}