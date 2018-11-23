package fr.imtatlantique.launeau

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Fragment representing one book.
 */
class BookFragment : Fragment() {
    private lateinit var book: Book

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        // Getting the book that was passed by the activity or the fragment
        val bundle = this.arguments
        if (bundle != null) {
            book = bundle.getParcelable("book")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.book_fragment, container, false)
        view.findViewById<TextView>(R.id.bookTitle).text = book.title
        view.findViewById<TextView>(R.id.bookPrice).text = book.price + "€"
        view.findViewById<TextView>(R.id.bookDescription).text = book.isbn
        return view
    }
}