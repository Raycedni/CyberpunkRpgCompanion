import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")

//    testBody = jacksonObjectMapper().readValue(testBodyJson)
    println(
        defaultJsonMapper().writerWithDefaultPrettyPrinter()
            .writeValueAsString(CyberpunkCharacter("Johnny", 300.5, 150.0))
    )
}

@Bean
fun defaultJsonMapper(): ObjectMapper {
    return jacksonObjectMapper().registerModule(Jdk8Module())
}