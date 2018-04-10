package si.dime.kotlin.tutorials.rest.booklibrary.db

import org.springframework.stereotype.Component
import si.dime.kotlin.tutorials.rest.booklibrary.model.Book
import javax.annotation.PostConstruct

@Component
class BooksDatabase
{
    //All of our books will live here
    private val books = mutableListOf<Book>()

    @PostConstruct
    private fun init()
    {
        // Fill our "database"
        books.add(Book("0765326353", "The Way of Kings", "Brandon Sanderson", "https://d.gr-assets.com/books/1448127430l/7235533.jpg"))
        books.add(Book("0345391802", "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "https://d.gr-assets.com/books/1327656754l/11.jpg"))
        books.add(Book("076531178X", "Mistborn: The Final Empire", "Brandon Sanderson", "https://d.gr-assets.com/books/1437254833l/68428.jpg"))
    }

    fun getBooks() = books

    fun addBook(book: Book): Boolean
    {
        books.firstOrNull { it.ISBN == book.ISBN } ?.let { return false }
        books.add(book)

        return true
    }
}