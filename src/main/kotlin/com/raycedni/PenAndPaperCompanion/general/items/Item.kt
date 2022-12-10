import java.util.UUID

open abstract class Item(
    open val itemClassId: Double,
    val name: String,
    val monetaryValue: Double,
    open val objectId: UUID = UUID.randomUUID(),
)