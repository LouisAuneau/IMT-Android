package fr.imtatlantique.launeau

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class BooksAdapter(context: Context, private val books: Array<Book>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        if (view == null) {
            val inflater: LayoutInflater = LayoutInflater.from(parent?.getContext());
            view = inflater.inflate(R.layout.book_item_view, parent, false)
        }
        val book = getItem(position)
        val bookItemView = view as BookItemView
        bookItemView.bindView(book)
        return bookItemView
    }

    override fun getItem(position: Int): Book = books[position]

    override fun getItemId(position: Int): Long {
        return books[position].hashCode().toLong()
    }

    override fun getCount(): Int = books.count()
}