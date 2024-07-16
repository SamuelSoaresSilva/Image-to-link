package com.samuel.ssilva.fileHandler.imageFile

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@RequestMapping("/api/imageFile")
private class ImageFileController(
    private val service: ImageFileService,
    ) {

    @PostMapping
    @Throws(IOException::class)
    fun uploadImage(@RequestParam image: MultipartFile): ResponseEntity<Any>? = service.saveValidatedImage(image)

    @GetMapping("/{imageName}")
    fun getImage(@PathVariable imageName: String): ResponseEntity<Any>? = service.returnValidatedImage(imageName)

    @DeleteMapping("/delete/{imageName}")
    fun deleteImage(@PathVariable imageName: String): Any? = service.removeImageFromDataBase(imageName)
}