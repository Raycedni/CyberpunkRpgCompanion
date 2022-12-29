package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.temporarypointchanges

import com.fasterxml.jackson.annotation.JsonIgnore

interface TemporaryEffectSource<T> {
    fun generateSourceDescription(): String

    @JsonIgnore
    fun getSourceClass(): Class<T>

    @JsonIgnore
    fun getSourceObject(): T
}