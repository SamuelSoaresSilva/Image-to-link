package com.samuel.ssilva.fileHandler.globalApi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GlobalApiController {

    @GetMapping
    fun isApiRunning(): ResponseEntity<String> = ResponseEntity("<h1>Api is running</h1><br/> <img src=\"https://i0.wp.com/motiongraphicsphoebe.wordpress.com/wp-content/uploads/2018/10/loading-animations-preloader-gifs-ui-ux-effects-18.gif?w=1000&h=&ssl=1\" alt=\"Running\">", HttpStatus.OK)
}