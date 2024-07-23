package com.samuel.ssilva.fileHandler.imageFile

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ImageFileRepository : JpaRepository<ImageFile, Long> {

    fun existsByName(name: String): Boolean
    fun findByName(name: String): ImageFile?

    @Query("SELECT new com.samuel.ssilva.fileHandler.imageFile.ImageFileSimpleResponse(i.id, i.name, i.type) FROM IMAGE_FILE_TB i")
    fun findAllImages(): List<ImageFileSimpleResponse>
}
