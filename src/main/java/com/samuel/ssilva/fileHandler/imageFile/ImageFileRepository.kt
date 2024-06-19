package com.samuel.ssilva.fileHandler.imageFile

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageFileRepository : JpaRepository<ImageFile, Long> {

}
