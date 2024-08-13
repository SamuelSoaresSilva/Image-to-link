package com.samuel.ssilva.fileHandler.imageFile

import jakarta.persistence.*

@Entity(name = "IMAGE_FILE_TB")
data class ImageFile(

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long? = null,
    val name: String? = null,

    val type: String? = null,

    @Column(length = 5000000, name = "img_byte", columnDefinition = "bytea")
    val imgByte: ByteArray? = null,

    val megabytes: Double? = null,

    ){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageFile

        if (id != other.id) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (imgByte != null) {
            if (other.imgByte == null) return false
            if (!imgByte.contentEquals(other.imgByte)) return false
        } else if (other.imgByte != null) return false
        if (megabytes != other.megabytes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (imgByte?.contentHashCode() ?: 0)
        result = 31 * result + (megabytes?.hashCode() ?: 0)
        return result
    }

}

class ImageFileResponse(val id: Long? = null,
                        val name: String? = null,
                        val type: String? = null,
                        val megabytes: Double? = null,
                        val url: String? = null,)



data class ImageFileSimpleResponse(
    val id: Long,
    val name: String,
    val type: String,
    val megabytes: Double,
)
