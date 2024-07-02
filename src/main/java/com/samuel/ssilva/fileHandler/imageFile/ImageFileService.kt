package com.samuel.ssilva.fileHandler.imageFile


import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class ImageFileService(
    private val repository: ImageFileRepository,

    ) {

    private fun saveImageInDataBase(image: MultipartFile): Any?{

        val imageFile: ImageFile = repository.save(
            ImageFile.Builder()
            .name(image.originalFilename)
            .type(image.contentType)
            .imgByte(ImageUtils.compressImage(image.bytes))
            .build())

        return if (imageFile == null) null else "File uploaded successfully: ${image.originalFilename};"
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

    private fun saveImage(image: MultipartFile): ResponseEntity<Any>?{
        val fileName: String? = image.originalFilename
        val path = "${System.getProperty("user.dir")}/src/main/resources/static/$fileName"
        try {
            image.transferTo(File(path))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(e.localizedMessage)
        }
        return ResponseEntity.ok().body("path: $path")
    }

}