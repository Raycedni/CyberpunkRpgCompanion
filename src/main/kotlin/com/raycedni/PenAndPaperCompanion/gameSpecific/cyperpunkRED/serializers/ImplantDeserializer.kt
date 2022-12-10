package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.Implant
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.ImplantFactory

class ImplantDeserializer : JsonDeserializer<Implant>() {
    override fun deserialize(jp: JsonParser?, ctxt: DeserializationContext?): Implant {
        val jsonNode = jp!!.codec.readTree<JsonNode>(jp)

        val implantId = jsonNode.get("itemClassId").numberValue().toDouble()
//        val implantObjectId = UUID.fromString(jsonNode.get("objectId").asText())

        return ImplantFactory.getFactory().buildImplantFromClassId(implantId)
    }

}