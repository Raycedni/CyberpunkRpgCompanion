package com.raycedni.PenAndPaperCompanion

import CyberpunkCharacter
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

class CyberpunkCharacterParameterResolver : ParameterResolver {
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type == CyberpunkCharacter::class.java
    }

    override fun resolveParameter(p0: ParameterContext?, p1: ExtensionContext?): Any {
        return CyberpunkCharacter("Johnny", 150.0, 150.0)
    }

}