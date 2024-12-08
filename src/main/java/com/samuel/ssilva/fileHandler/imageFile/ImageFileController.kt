package com.samuel.ssilva.fileHandler.imageFile

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.servlet.http.HttpServletRequest
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
    fun uploadImage(@RequestParam image: MultipartFile): ResponseEntity<out Any> = service.saveValidatedImage(image)

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Imagem encontrada com sucesso",
                content = [
                    Content(
                        mediaType = "image/png",
                        schema = Schema(type = "string", format = "binary")
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Imagem não encontrada",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ImageNameResponse::class)
                    )
                ]
            )
        ]
    )
    @GetMapping("/{imageName}")
    fun getImage(@PathVariable imageName: String): ResponseEntity<out Any> = service.returnValidatedImage(imageName)

    @GetMapping("/info/{imageName}")
    fun getImageInfo(@PathVariable imageName: String, request: HttpServletRequest): ResponseEntity<Any>? = service.returnImageInfo(imageName, request)

    @GetMapping
    fun getAllImages(): ResponseEntity<Any>? = service.returnAllImages()

    @DeleteMapping("/delete/{imageName}")
    fun deleteImage(@PathVariable imageName: String): ResponseEntity<ImageNameResponse> = service.deleteImage(imageName)
}