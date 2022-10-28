
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.Body

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")
    var testBody = Body(5.0)
    val testBodyJson:String = jacksonObjectMapper().writeValueAsString(testBody)
//    testBody = jacksonObjectMapper().readValue(testBodyJson)
    println(testBodyJson)


}