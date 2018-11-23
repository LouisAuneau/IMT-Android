package fr.imtatlantique.launeau

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class BooksFragment : Fragment() {

    private lateinit var listener: OnBookClickListener
    private lateinit var booksListView: ListView
    private lateinit var books: Array<Book>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        when (context) {
            is OnBookClickListener -> listener = context
            else -> null
        }

        // Getting books that were passed by the activity
        val bundle = this.arguments
        if (bundle != null) {
            books = bundle.getParcelableArray("books") as Array<Book>
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.books_fragment, container, false)
        booksListView = view.findViewById(R.id.booksListView) as ListView
        booksListView.adapter = BooksAdapter(context!!, books)
        return view
    }

    interface OnBookClickListener {
        fun onNext()
    }

}
