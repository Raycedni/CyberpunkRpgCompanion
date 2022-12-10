import java.util.UUID

interface Character {
    var name: String
    var health: Double
    var inventory: MutableMap<UUID, Any>

    fun addItemToInventory(item: Item)
    fun removeItemFromInventory()

}