package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes

import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.Implant
import java.util.Optional


class SkillValues(var points: Double = 0.0) {
    private var chippedBy: Optional<Implant> = Optional.empty()

    fun installSkillChip(implant: Implant) {
        chippedBy = Optional.of(implant)
    }

    fun isSkillChipped() = chippedBy.isPresent
}