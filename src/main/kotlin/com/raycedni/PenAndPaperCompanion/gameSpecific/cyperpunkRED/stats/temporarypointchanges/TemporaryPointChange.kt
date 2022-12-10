package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges

class TemporaryPointChange(
    val valueChange: Int,
    val typeOfTemporaryPointChange: TypesOfTemporaryPointChangeEnum = TypesOfTemporaryPointChangeEnum.NEUTRAL,
    private val condition: () -> Boolean,
    val causedBy: Any
) {
    fun isStillValid(): Boolean = condition.invoke()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TemporaryPointChange

        if (valueChange != other.valueChange) return false
        if (typeOfTemporaryPointChange != other.typeOfTemporaryPointChange) return false
        if (condition != other.condition) return false

        return true
    }
}