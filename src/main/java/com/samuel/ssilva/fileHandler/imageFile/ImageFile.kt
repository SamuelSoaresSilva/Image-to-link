package com.samuel.ssilva.fileHandler.imageFile

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "IMAGE_FILE_TB")
class ImageFile {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null

    private val name: String? = null
    private val type: String? = null

    private val filePath: String? = null

}