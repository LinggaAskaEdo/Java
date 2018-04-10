package si.dime.kotlin.tutorials.rest.booklibrary.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloSpringBootController
{
    @RequestMapping(value = ["/"])
    fun helloSpringBoot() = "Hello SpringBoot"
}