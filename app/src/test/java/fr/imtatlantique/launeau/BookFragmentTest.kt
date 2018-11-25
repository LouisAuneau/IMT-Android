package fr.imtatlantique.launeau

import android.os.Bundle
import android.support.v4.app.FragmentController
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.util.FragmentTestUtil

@RunWith(RobolectricTestRunner::class)
class BookFragmentTest {

    private lateinit var fragment: BookFragment
    private var isbn: String = "isbn1234"
    private var title: String = "title"
    private var price: String = "12"
    private var cover: String = "http://henri-potier.xebia.fr/hp0.jpg"

    @Before
    fun setUp() {
        val book = Book(isbn, title, price, cover)
        val bundle = Bundle()
        bundle.putParcelable("book", book)
        fragment.arguments = bundle

//        Robolectric.buildFragment(BookFragment::class.java, bundle)
//        TODO
    }
}