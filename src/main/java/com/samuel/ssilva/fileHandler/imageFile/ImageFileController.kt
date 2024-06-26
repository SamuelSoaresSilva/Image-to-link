package com.samuel.ssilva.fileHandler.imageFile

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@RequestMapping("/api/imageFile")
private class ImageFileController(
    private val service: ImageFileService,
    ) {

    @PostMapping
    @Throws(IOException::class)
    fun uploadImage(@RequestParam image: MultipartFile): ResponseEntity<Any>? {
        val imageUtils: ImageFileUtils = ImageFileUtils()
        print(imageUtils.imageToBase64(image))
        return service.saveValidatedImage(image)
    }

}