package com.samuel.ssilva.fileHandler.imageFile

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@RequestMapping("/api/image")
private class ImageFileController(
    private val service: ImageFileService,
    ) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Throws(IOException::class)
    fun uploadImage(@RequestParam image: MultipartFile): ResponseEntity<Any>? = service.saveValidatedImage(image)

    @GetMapping("/{imageName}")
    fun getImage(@PathVariable imageName: String): ResponseEntity<Any>? = service.returnValidatedImage(imageName)

    @GetMapping("/info/{imageName}")
    fun getImageInfo(@PathVariable imageName: String, request: HttpServletRequest): ResponseEntity<Any>? = service.returnImageInfo(imageName, request)

    @GetMapping
    fun getAllImages(): ResponseEntity<Any>? = service.returnAllImages()

    @DeleteMapping("/delete/{imageName}")
    fun deleteImage(@PathVariable imageName: String): ResponseEntity<DeleteImageView> = service.deleteImage(imageName)
}