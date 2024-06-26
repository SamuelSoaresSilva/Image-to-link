package com.samuel.ssilva.fileHandler.imageFile

import org.apache.commons.io.FileUtils
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.util.*

@Service
class ImageFileService(
    private val repository: ImageFileRepository,
) {


    fun saveImageInDataBase(image: MultipartFile): ResponseEntity<Any>?{
        val utils: ImageFileUtils = ImageFileUtils()


        return null
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

    fun saveValidatedImage(image: MultipartFile): ResponseEntity<Any>? {
        return when{
            image.isEmpty -> ResponseEntity.badRequest().body("Error by receiving the image")
            image.originalFilename?.contains(" ") == true -> ResponseEntity.badRequest().body("File name can not have spaces")
            image.originalFilename?.let { repository.existsByName(it) } == true -> ResponseEntity.badRequest().body("A file with this name already exists")
            else -> saveImage(image)
        }
    }


}