import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.Body
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.Cool
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.Empathy
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.Intelligence
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.Reflexes
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeClasses.Tech

class CyberpunkCharacter(
    override var name: String,
    override var health: Double,
    override var inventory: MutableMap<String, Item>,
    private val attributes: CyberpunkRedCharacterAttributes

) : Character {
    override fun addItemToInventory(item: Item) {
        TODO("Not yet implemented")
    }

    override fun removeItemFromInventory() {
        TODO("Not yet implemented")
    }

    fun getCopyOfAttributes(): CyberpunkRedCharacterAttributes {
        return attributes.copy()
    }

    data class CyberpunkRedCharacterAttributes(
        val body: Body,
        val cool: Cool,
        val empathy: Empathy,
        val intelligence: Intelligence,
        val reflexes: Reflexes,
        val tech: Tech
    )
}