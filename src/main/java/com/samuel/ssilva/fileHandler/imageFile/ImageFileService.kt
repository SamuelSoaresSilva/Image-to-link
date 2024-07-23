package com.samuel.ssilva.fileHandler.imageFile


import com.samuel.ssilva.fileHandler.imageFile.ImageUtils.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageFileService(
    private val repository: ImageFileRepository
) {
    // TODO: define a size limit for receiving images
    private fun saveImageInDataBase(image: MultipartFile): String{
        repository.save(
            ImageFile.Builder()
            .name(image.originalFilename)
            .type(image.contentType)
            .imgByte(compressImage(image.bytes))
            .build())

        return "File uploaded successfully: ${image.originalFilename};"
    }

    fun saveValidatedImage(image: MultipartFile): ResponseEntity<Any>? {
        return when{
            image.isEmpty -> ResponseEntity.badRequest().body("Error by receiving the image")
            image.originalFilename?.contains(" ") == true -> ResponseEntity.badRequest().body("File name can not have spaces")
            image.originalFilename?.let { repository.existsByName(it) } == true -> ResponseEntity.badRequest().body("A file with this name already exists")
            else -> {
                val uploadImage = saveImageInDataBase(image)
            if (uploadImage == null) ResponseEntity.badRequest().body("Error by receiving the image") else ResponseEntity.ok().body(uploadImage)
            }
        }
    }

    private fun returnImageFromDataBase(name:String): ByteArray{
        val image: ImageFile? = repository.findByName(name)
        val imageData: ByteArray = decompressImage(image?.imgByte)
        return imageData
    }

    fun returnValidatedImage(name: String): ResponseEntity<Any>? {
        return when {

            (name.equals(null) || name.isBlank() || name.contains(" ")) ->
                return ResponseEntity.badRequest()
                .body("The file you are looking for must have a valid name")

            name.let { !repository.existsByName(it) } ->
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The file you are looking for doesn't exists")

            else -> {
                ResponseEntity.ok()
                    .contentType(MediaType.valueOf("image/png"))
                    .body(returnImageFromDataBase(name))
            }

        }
    }
    fun returnImageInfo(name: String, request: HttpServletRequest): ResponseEntity<Any>? {
        val image: ImageFile? = repository.findByName(name)
        if (image == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The file you are looking for doesn't exists")
        } else {
            val imageUrl = request.requestURL.toString().replace("/info", "")
            val imageResponse = ImageFileResponse(image?.id, image?.name, image?.type, imageUrl)
            return ResponseEntity.ok().body(imageResponse)
        }
    }

    fun removeImageFromDataBase(name: String): ResponseEntity<Any> {
        val image: ImageFile? = repository.findByName(name)
        if (image == null) {
            return ResponseEntity.status(404).body("The file doesn't exists")
        }else{
            repository.deleteById(image.id)
            return ResponseEntity.ok().body("The image ${image.name} with id ${image.id} was deleted")
        }
    }

}