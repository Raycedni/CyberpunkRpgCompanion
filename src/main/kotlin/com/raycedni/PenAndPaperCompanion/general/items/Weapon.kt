package com.raycedni.PenAndPaperCompanion.general.items

import Item

interface Weapon : Item {
    fun getTotalDps(): Double
}