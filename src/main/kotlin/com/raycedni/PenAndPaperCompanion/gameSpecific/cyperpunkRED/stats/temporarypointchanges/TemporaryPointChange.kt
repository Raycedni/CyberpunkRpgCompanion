package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges

class TemporaryPointChange(
    val valueChange: Int,
    val typeOfTemporaryPointChange: TypesOfTemporaryPointChangeEnum = TypesOfTemporaryPointChangeEnum.NEUTRAL,
    private val condition: () -> Boolean,
    val effectSource: TemporaryEffectSource<*>
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

    override fun hashCode(): Int {
        var result = valueChange
        result = 31 * result + typeOfTemporaryPointChange.hashCode()
        result = 31 * result + condition.hashCode()
        result = 31 * result + effectSource.hashCode()
        return result
    }
}