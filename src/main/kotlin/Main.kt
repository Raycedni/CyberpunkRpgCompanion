import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.ImplantFactory
import org.springframework.context.annotation.Bean

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")

//    testBody = jacksonObjectMapper().readValue(testBodyJson)
    val originalChar = CyberpunkCharacter("Johnny", 300.5, 150.0)
    val jsonChar = defaultJsonMapper().readValue(getCPCharAsJSON(originalChar), CyberpunkCharacter::class.java)

//    println(originalChar.getAttributes()["Body"]?.attributeSkillMap)
//    println("${originalChar.getAttributes()["Body"]?.attributeSkillMap?.equals(jsonChar.getAttributes()["Body"]?.attributeSkillMap)}")
//    println(getCPCharAsJSON(originalChar))
//    println(getCPCharAsJSON(defaultJsonMapper().readValue(getCPCharAsJSON(originalChar), CyberpunkCharacter::class.java)))

    val sandy = ImplantFactory.getFactory().buildSandevistan()
    originalChar.installImplant(sandy)

    println(getCPCharAsJSON(originalChar))
}

fun getCPCharAsJSON(character: CyberpunkCharacter): String =
    defaultJsonMapper().writerWithDefaultPrettyPrinter().writeValueAsString(character)

@Bean
fun defaultJsonMapper(): ObjectMapper {
    return jacksonObjectMapper().registerModule(Jdk8Module())
}

var currentId = 0.0

@Bean
fun idgen(): Double {
    return currentId++
}