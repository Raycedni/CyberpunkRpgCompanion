package com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.raycedni.PenAndPaperCompanion.gameSpecific.cyperpunkRED.implants.Implant

class ImplantSerializer : JsonSerializer<Implant>() {
    override fun serialize(implant: Implant?, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
        if (implant != null) {
            jsonGenerator.writeStartObject()
            jsonGenerator.writeNumberField("itemClassId", implant.itemClassId)
            jsonGenerator.writeStringField("objectId", implant.objectId.toString())
            jsonGenerator.writeStringField("name", implant.name)
        }
    }

}