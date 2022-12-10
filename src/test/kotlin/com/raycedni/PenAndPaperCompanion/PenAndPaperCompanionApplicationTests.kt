package com.raycedni.PenAndPaperCompanion

import CyberpunkCharacter
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.ImplantFactory
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.BodySkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.CoolSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.EmpathySkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.IntelligenceSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.ReflexesSkillListEnum
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.stats.attributes.attributeSkillListEnums.TechSkillListEnum
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(CyberpunkCharacterParameterResolver::class)
class PenAndPaperCompanionApplicationTests {

    val defaultJsonMapper = jacksonObjectMapper().registerModule(Jdk8Module())
    private val listOfAllDefaultAttributes = listOf("Body", "Cool", "Empathy", "Intelligence", "Reflexes", "Tech")
    private val listOfAllSkillEnums = listOf(
        enumValues<BodySkillListEnum>(),
        enumValues<CoolSkillListEnum>(),
        enumValues<EmpathySkillListEnum>(),
        enumValues<IntelligenceSkillListEnum>(),
        enumValues<ReflexesSkillListEnum>(),
        enumValues<TechSkillListEnum>()
    )

    @Test
    fun contextLoads() {
    }

    @Test
    fun characterJsonParse(character: CyberpunkCharacter) {
        character.installImplant(ImplantFactory.getFactory().buildSandevistan())
        val characterJson = getCPCharAsJSON(character)
        System.out.println(characterJson)

        val rereadCharacter = defaultJsonMapper.readValue(characterJson, CyberpunkCharacter::class.java)
        System.out.println(getCPCharAsJSON(rereadCharacter))


        assert(character.equals(rereadCharacter))
    }

    @Test
    fun characterContainsAllAttributes(character: CyberpunkCharacter) {
        assert(character.getAttributes().keys.containsAll(listOfAllDefaultAttributes))
    }

    @Test
    fun characterContainsAllDefaultSkills(character: CyberpunkCharacter) {
        val requiredSkills = mutableListOf<String>()
        listOfAllSkillEnums.forEach { enumValue -> enumValue.forEach { entriesInEnum -> requiredSkills.add(entriesInEnum.name) } }
        val presentSkills = mutableListOf<String>()
        character.getAttributes().values.forEach { attribute ->
            attribute.getSkills().forEach { presentSkills.add(it.key) }
        }

        assert(presentSkills.containsAll(requiredSkills))
    }

    private fun getCPCharAsJSON(character: CyberpunkCharacter): String = defaultJsonMapper.writeValueAsString(character)
}
