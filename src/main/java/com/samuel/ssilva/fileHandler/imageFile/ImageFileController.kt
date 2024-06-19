package com.samuel.ssilva.fileHandler.imageFile

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@RequestMapping("/api/imageFile")
private class ImageFileController {

    @PostMapping
    @Throws(IOException::class)
    fun uploadImage(@RequestParam image: MultipartFile?): ResponseEntity<Any> {
        if (image == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.ok().body(image.originalFilename)
    }

}