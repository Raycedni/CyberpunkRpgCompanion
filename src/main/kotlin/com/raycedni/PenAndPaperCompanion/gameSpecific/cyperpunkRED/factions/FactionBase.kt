package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.factions

open class FactionBase {
    val groupName:String = ""
    val friendlyFactions:List<FactionBase> = mutableListOf()
    val hostileFactions:List<FactionBase> = mutableListOf()
    val neutralFactions:List<FactionBase> = mutableListOf()
}