package si.dime.kotlin.tutorials.rest.booklibrary.model

class Book()
{
    lateinit var ISBN: String
    lateinit var title: String
    lateinit var author: String
    var coverURL: String? = null

    constructor(ISBN: String, title: String, author: String, coverUrl: String? = null) : this()
    {
        this.ISBN = ISBN
        this.title = title
        this.author = author
        this.coverURL = coverUrl
    }
}