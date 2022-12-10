package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges

import com.fasterxml.jackson.annotation.JsonIgnoreType

@JsonIgnoreType
class TemporaryPointHandler {
    protected var temporaryPointChangeList: MutableList<TemporaryPointChange> = mutableListOf()


    fun getSumOfAllEffects(): Int {
        var sumOfAllEffects = 0
        checkForAndRemoveInvalidEffects(temporaryPointChangeList)
        return temporaryPointChangeList.filter { it.isStillValid() }.toListOfInt().sum()
    }

    fun addTemporaryEffect(temporaryPointChange: TemporaryPointChange) =
        temporaryPointChangeList.add(temporaryPointChange)

    fun Iterable<TemporaryPointChange>.toListOfTemporaryEffects() = this.toList()

    fun getallTemporaryEffects() = temporaryPointChangeList.toList()
    fun cleanseAllTemporaryEffects() = temporaryPointChangeList.removeAll { true }
    fun cleanseAllNegativeEffectsOfType(temporaryPointChangeType: TypesOfTemporaryPointChangeEnum) =
        temporaryPointChangeList.removeAll { it.typeOfTemporaryPointChange == temporaryPointChangeType }

    private fun checkForAndRemoveInvalidEffects(list: MutableList<TemporaryPointChange>): MutableList<TemporaryPointChange> {
        list.forEach {
            if (!it.isStillValid())
                list.remove(it)
        }
        return list
    }

    private fun Iterable<TemporaryPointChange>.toListOfInt(): List<Int> {
        val listOfPoints = mutableListOf<Int>()
        this.forEach { listOfPoints.add(it.valueChange) }
        return listOfPoints
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TemporaryPointHandler

        if (temporaryPointChangeList != other.temporaryPointChangeList) return false

        return true
    }

    override fun hashCode(): Int {
        return temporaryPointChangeList.hashCode()
    }
}