package fr.imtatlantique.launeau

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.imtatlantique.louisauneau.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger

class BooksActivity : AppCompatActivity() {

    private var books: Array<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getBooks()

        setContentView(R.layout.activity_books)
    }

    /**
     * Get books from the API. Displays a toast in case no book is found.
     */
    fun getBooks() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://henri-potier.xebia.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val booksApi = retrofit.create(BooksService::class.java)
        booksApi.getAll().enqueue(object : Callback<Array<Book>> {
            override fun onFailure(call: Call<Array<Book>>, t: Throwable) {
                Toast.makeText(this@BooksActivity, R.string.error_books_retrievement, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Array<Book>>, response: Response<Array<Book>>) {
                this@BooksActivity.books = response.body()
                Logger.getLogger(this@BooksActivity::class.java.name).info(books.toString())
            }
        })
    }
}
