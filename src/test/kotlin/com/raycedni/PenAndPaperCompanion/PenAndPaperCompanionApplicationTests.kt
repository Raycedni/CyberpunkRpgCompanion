package com.raycedni.PenAndPaperCompanion

import CyberpunkCharacter
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(CyberpunkCharacterParameterResolver::class)
class PenAndPaperCompanionApplicationTests {

    val defaultJsonMapper = jacksonObjectMapper().registerModule(Jdk8Module())

    @Test
    fun contextLoads() {
    }

    @Test
    fun characterJsonParse(character: CyberpunkCharacter) {
        val defaultCPCharacterJson = getCPCharAsJSON(character)

        assert(character.equals(defaultJsonMapper.readValue(defaultCPCharacterJson, CyberpunkCharacter::class.java)))
    }

    fun getCPCharAsJSON(character: CyberpunkCharacter): String = defaultJsonMapper.writeValueAsString(character)
}
