package com.samuel.ssilva.fileHandler.utils

import com.samuel.ssilva.fileHandler.imageFile.ImageNameResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class NameValidator {

    private val invalidCharacters = "[<>:\"/|?* ]".toRegex()

    fun validateName(name: String?): ResponseEntity<ImageNameResponse>?{
        return if (invalidCharacters.containsMatchIn(name!!))
            ResponseEntity.status(400).body(ImageNameResponse(name, "invalid name or characters"))
        else
            null
    }

}