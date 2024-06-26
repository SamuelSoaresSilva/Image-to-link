package com.samuel.ssilva.fileHandler.imageFile

import jakarta.persistence.*

@Entity(name = "IMAGE_FILE_TB")
class ImageFile {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private val id: Long? = null

    private val name: String? = null
    private val type: String? = null

    @Column(length = 5000000)
    private val imgByte: ByteArray? = null

    constructor()
}