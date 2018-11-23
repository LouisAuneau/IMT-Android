package fr.imtatlantique.launeau

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

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
        displayImage(book.cover, view.findViewById<TextView>(R.id.bookCover) as ImageView)
        return view
    }

    private fun displayImage(url: String, imageView: ImageView) {
        Picasso.get().load(url).resize(325, 500).into(imageView)
    }
}