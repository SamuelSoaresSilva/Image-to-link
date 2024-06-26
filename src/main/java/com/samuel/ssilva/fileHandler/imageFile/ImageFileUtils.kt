package com.samuel.ssilva.fileHandler.imageFile

import org.springframework.web.multipart.MultipartFile
import java.io.IOException


class ImageFileUtils {

    fun imageToBase64(image: MultipartFile): Any?{

        val imageContent: ByteArray = image.bytes

            return imageContent.toString()
    }

}