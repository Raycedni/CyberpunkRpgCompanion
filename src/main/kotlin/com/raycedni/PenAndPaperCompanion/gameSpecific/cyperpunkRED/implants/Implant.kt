package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants

import CyberpunkCharacter
import Item

class Implant(
    override val name: String,
    override val monetaryValue: Double,
//    val implantType: ImplantType,
//    val listOfAffects: List<>
    private val affectOnCharacter: List<(CyberpunkCharacter) -> Double>
) : Item {
    fun installImplant(implantReceiver: CyberpunkCharacter) {
        affectOnCharacter.forEach { implantReceiver.removeHumanity(it.invoke(implantReceiver)) }
    }
}