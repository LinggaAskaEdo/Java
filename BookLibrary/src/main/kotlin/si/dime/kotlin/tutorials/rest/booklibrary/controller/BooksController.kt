package si.dime.kotlin.tutorials.rest.booklibrary.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import si.dime.kotlin.tutorials.rest.booklibrary.db.BooksDatabase

@RestController
class BooksController
{
    @Autowired
    private lateinit var database: BooksDatabase

    @RequestMapping("", method = arrayOf(RequestMethod.GET))
    fun books() = database.getBooks()


}