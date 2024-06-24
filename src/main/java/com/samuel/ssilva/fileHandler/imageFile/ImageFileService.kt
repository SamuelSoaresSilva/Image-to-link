package com.samuel.ssilva.fileHandler.imageFile

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class ImageFileService(
    private val repository: ImageFileRepository,
) {

    fun saveImage(image: MultipartFile): ResponseEntity<String>?{
        when{
            image.isEmpty -> return ResponseEntity.badRequest().body("Error by receiving the image")
            image.originalFilename.contains(" ") -> return ResponseEntity.badRequest().body("File name can not have spaces")
            repository.existsByName(image.originalFilename) -> return ResponseEntity.badRequest().body("A file with this name already exists")
        }
        val fileName: String = image.originalFilename
        val path = "${System.getProperty("user.dir")}/src/main/resources/static/$fileName"
        try {
            image.transferTo(File(path))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(e.localizedMessage)
        }
        return ResponseEntity.ok().body("path: $path")
    }
}