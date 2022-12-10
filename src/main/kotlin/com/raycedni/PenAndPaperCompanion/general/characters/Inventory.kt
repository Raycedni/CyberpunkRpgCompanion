package com.raycedni.PenAndPaperCompanion.general.characters

import Item
import org.springframework.data.crossstore.ChangeSetPersister
import java.util.UUID

class Inventory {
    private var inventoryContent: MutableMap<UUID, Any> = mutableMapOf()

    fun addObjectToInventory(item: Any) {
        if (item is Item)
            inventoryContent.putIfAbsent(item.objectId, item)
        else
            inventoryContent.putIfAbsent(UUID.randomUUID(), item)
    }

    fun removeItemFromInventory(uuidOfItem: UUID) {
        if (inventoryContent.containsKey(uuidOfItem))
            inventoryContent.remove(uuidOfItem)
        else
            throw ChangeSetPersister.NotFoundException()
    }

    fun getInventoryContent() = inventoryContent
    fun getInventoryContentFilteredBy(listOfPredicates: List<() -> Boolean>): MutableMap<UUID, Any> {
        val tempList = inventoryContent.toMutableMap()
        listOfPredicates.forEach { predicate -> tempList.filter { predicate.invoke() } }
        return tempList
    }
}