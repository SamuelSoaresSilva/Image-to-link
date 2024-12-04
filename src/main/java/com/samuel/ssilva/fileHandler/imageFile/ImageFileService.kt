package com.samuel.ssilva.fileHandler.imageFile


import com.samuel.ssilva.fileHandler.imageFile.ImageUtils.*
import com.samuel.ssilva.fileHandler.utils.NameValidator
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageFileService(
    private val repository: ImageFileRepository,
    private val validator: NameValidator
) {
    // TODO: define a size limit for receiving images
    private fun saveImageInDataBase(image: MultipartFile): String{
        val imageName = image.originalFilename
        repository.save(
            ImageFile(
                name = imageName,
                type = getFileExtension(imageName),
                imgByte = compressImage(image.bytes),
                megabytes = bytesToMegabytes(image.size)
                )
            )
        return imageName!!
    }

    fun saveValidatedImage(image: MultipartFile): ResponseEntity<out Any> {
        validator.validateName(image.originalFilename)?.let{
            return it
        }
        return when{
            image.isEmpty -> ResponseEntity.badRequest().body("Error by receiving the image")
            image.originalFilename?.let { repository.existsByName(it) } == true -> ResponseEntity.badRequest().body("A file with this name already exists")
            else -> {
                val uploadImage = saveImageInDataBase(image)
                ResponseEntity.ok().body(ImageNameResponse(uploadImage, "saved"))
            }
        }
    }

    private fun returnImageFromDataBase(name:String): ByteArray{
        val image: ImageFile? = repository.findByName(name)
        val imageData: ByteArray = decompressImage(image?.imgByte)
        return imageData
    }

    fun returnValidatedImage(name: String): ResponseEntity<out Any> {
        validator.validateName(name)?.let{
            return it
        }
        return when {
            name.let { !repository.existsByName(it) } ->
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ImageNameResponse(name, "not found"))

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
            val imageResponse = ImageFileResponse(image?.id, image?.name, image?.type, image?.megabytes, imageUrl)
            return ResponseEntity.ok().body(imageResponse)
        }
    }

    fun deleteImage(name: String): ResponseEntity<ImageNameResponse> {
        val image: Int = repository.deleteByName(name)
        return if (image == 0) ResponseEntity.status(HttpStatus.NOT_FOUND).body(ImageNameResponse(name, "not found"))
        else ResponseEntity.status(HttpStatus.OK).body(ImageNameResponse(name, "deleted"))
    }

    fun returnAllImages(): ResponseEntity<Any>? {
        val imageList: List<ImageFileSimpleResponse?> = repository.findAllImages()
        return if (imageList.isEmpty()) ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no images") else ResponseEntity.ok().body(imageList)
    }


}