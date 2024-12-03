package com.samuel.ssilva.fileHandler.globalApi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.swing.text.html.HTML

@RestController
@RequestMapping("/api")
class GlobalApiController {

    @GetMapping
    fun isApiRunning(): ResponseEntity<String> {
        val gifUrl: String = "https://i0.wp.com/motiongraphicsphoebe.wordpress.com/wp-content/uploads/2018/10/loading-animations-preloader-gifs-ui-ux-effects-18.gif?w=1000&h=&ssl=1"
        val html: String =
                "<body style=\"padding: 0; margin: 0; user-drag: none; user-select: none;\n" +
                        "  user-drag: none;\n" +
                        "  user-select: none;\n" +
                        "  -moz-user-select: none;\n" +
                        "  -webkit-user-drag: none;\n" +
                        "  -webkit-user-select: none;\n" +
                        "  -ms-user-select: none;\n" +
                        "  pointer-events: none;\">\n" +
                        "    <div style=\"width: 100vw; height: 100vh; display: flex; align-items: center; justify-content: center; flex-direction: column; \">\n" +
                        "       <H1>Service is running</H1>\n" +
                        "       <img\n" +
                        "               style=\"user-drag: none;\n" +
                        "                      user-select: none;\n" +
                        "                      -moz-user-select: none;\n" +
                        "                      -webkit-user-drag: none;\n" +
                        "                      -webkit-user-select: none;\n" +
                        "                      -ms-user-select: none;\n" +
                        "                      pointer-events: none;\"\n" +
                        "               src=\"${gifUrl}\" alt=\"Running\">\n" +
                        "   </div>\n" +
                        "</body>"
        return ResponseEntity(html, HttpStatus.OK)
    }
}