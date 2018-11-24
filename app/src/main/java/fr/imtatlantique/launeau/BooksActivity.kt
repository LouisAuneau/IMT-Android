package fr.imtatlantique.launeau

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.imtatlantique.louisauneau.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Main activity launched on startup. It mainly manages book retrievement (getBooks()) and fragment loading (displayBooks).
 */
class BooksActivity : AppCompatActivity(), BooksFragment.OnBookClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        getBooks()
    }

    private var books: Array<Book>? = null
    private var book: Book? = null
    private var booksAPI: String = "http://henri-potier.xebia.fr/"

    /**
     * Get books from the API. Displays a toast in case no book is found.
     */
    fun getBooks() {
        val retrofit = Retrofit.Builder()
            .baseUrl(booksAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val booksApi = retrofit.create(BooksService::class.java)
        booksApi.getAll().enqueue(object : Callback<Array<Book>> {
            override fun onFailure(call: Call<Array<Book>>, t: Throwable) {
                Toast.makeText(this@BooksActivity, R.string.error_books_retrievement, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Array<Book>>, response: Response<Array<Book>>) {
                books = response.body()!!
                displayBooks(books)
            }
        })
    }

    /**
     * Displays the list of books through the fragment.
     */
    fun displayBooks(books: Array<Book>?) {
        // Generating the fragment and it arguments.
        val fragment = BooksFragment()
        val bundle = Bundle()
        bundle.putParcelableArray("books", books)
        fragment.arguments = bundle

        // Setting the frame in which the fragment will be displayed depending on the orientation.
        val frame: Int =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                R.id.leftBooksFrame
            else R.id.mainFrame

        // Displaying the fragment in the selected frame.
        supportFragmentManager
            .beginTransaction()
            .replace(frame, fragment, BooksFragment::class.java.name)
            .commit()
    }

    /**
     * Displays book details when a book is clicked.
     */
    override fun onBookClicked(book: Book?) {
        // Generating the fragment with the book to display
        val fragment = BookFragment()
        val bundle = Bundle()
        bundle.putParcelable("book", book)
        fragment.arguments = bundle

        // Setting state
        this.book = book

        // Setting the frame in which the fragment will be displayed depending on the orientation.
        val frame: Int =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                R.id.rightBooksFrame
            else R.id.mainFrame

        // Displaying the fragment in the selected frame.
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(BooksFragment::class.java.name)
            .replace(frame, fragment, BookFragment::class.java.name)
            .commit()
    }

    /**
     * Saving list of books and the current book in the state in case the activity flow is stopped.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        if (book != null)
            outState?.putParcelable("book", book)

        if (books != null)
            outState?.putParcelableArray("books", books)

        super.onSaveInstanceState(outState)
    }

    /**
     * Restoring the state (list of books and selected book) if they were saved.
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        book = savedInstanceState?.getParcelable("book")
        books = savedInstanceState?.getParcelableArray("books") as Array<Book>?

        if (book != null)
            onBookClicked(book)

        if (books != null)
            displayBooks(books)
    }

}
