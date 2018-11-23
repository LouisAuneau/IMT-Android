package fr.imtatlantique.launeau

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

class BookItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    var title: TextView? = null
    var price: TextView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        title = findViewById(R.id.bookItemName)
        price = findViewById(R.id.bookItemPrice)
    }

    fun bindView(book: Book?) {
        title?.text = book?.title
        price?.text = book?.price.toString()
    }
}
