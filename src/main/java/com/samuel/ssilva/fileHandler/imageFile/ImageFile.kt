package com.samuel.ssilva.fileHandler.imageFile

import jakarta.persistence.*

@Entity(name = "IMAGE_FILE_TB")
data class ImageFile (
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long? = null,

    val name: String? = null,
    val type: String? = null,

    @Column(length = 5000000, name = "img_byte", columnDefinition = "bytea")
    val imgByte: ByteArray? = null,


    ){
    class Builder {
        private var id: Long? = null
        private var name: String? = null
        private var type: String? = null
        private var imgByte: ByteArray? = null

        fun id(id: Long?) = apply { this.id = id }
        fun name(name: String?) = apply { this.name = name }
        fun type(type: String?) = apply { this.type = type }
        fun imgByte(imgByte: ByteArray?) = apply { this.imgByte = imgByte }

        fun build() = ImageFile(id, name, type, imgByte)
    }

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

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (imgByte?.contentHashCode() ?: 0)
        return result
    }
}

class ImageFileResponse(val id: Long? = null,
                        val name: String? = null,
                        val type: String? = null)


