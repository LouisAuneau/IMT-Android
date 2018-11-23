package fr.imtatlantique.louisauneau

import fr.imtatlantique.launeau.Book
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface for book retrievement through an API.
 */
interface BooksService {
    @GET("books")
    fun getAll(): Call<Array<Book>>
}
