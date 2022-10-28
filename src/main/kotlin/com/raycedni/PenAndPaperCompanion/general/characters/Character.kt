interface Character{
    var name:String
    var health:Double
    var inventory:MutableMap<String, Item>

    fun addItemToInventory(item: Item)
    fun removeItemFromInventory()

}