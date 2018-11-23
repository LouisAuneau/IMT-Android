package fr.imtatlantique.launeau

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import fr.imtatlantique.louisauneau.BooksService
import kotlinx.android.synthetic.main.activity_books.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger

class BooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        getBooks()
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
                displayBooks(response.body()!!)
            }
        })
    }

    /**
     * Displays the list of books through the fragment.
     */
    fun displayBooks(books: Array<Book>) {
        val fragment = BooksFragment()
        val bundle = Bundle()
        bundle.putParcelableArray("books", books)
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentContainer, fragment, BooksFragment::class.java.name)
            .commit()
    }
}
