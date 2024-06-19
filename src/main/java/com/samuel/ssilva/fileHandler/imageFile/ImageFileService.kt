package com.samuel.ssilva.fileHandler.imageFile

import jakarta.servlet.ServletContext
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import java.io.File

class ImageFileService(
    private val servlet: ServletContext,
) {

    fun saveImage(image: MultipartFile): ResponseEntity<String>?{
        if (image.isEmpty){
            return ResponseEntity.badRequest().body("Error by receiving the image")
        }
        try {
            val fileName:String = image.originalFilename;
            val path: String = servlet.getRealPath("/")+ "resources/" + fileName;
            image.transferTo(File(path))
        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }
}