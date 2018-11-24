package fr.imtatlantique.launeau

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class RobolectricBooksActivityTest {
    private lateinit var activity: BooksActivity
    private lateinit var book: Book
    private var isbn: String = "isbn1234"
    private var title: String = "title"
    private var price: String = "12"
    private var cover: String = "http://henri-potier.xebia.fr/hp0.jpg"

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(BooksActivity::class.java)
        book = Book(isbn, title, price, cover)
    }

    @Test
    fun should_have_books_fragment() {
        Assert.assertNotNull(activity.supportFragmentManager.findFragmentByTag(BooksFragment::class.java.name))
    }

    @Test
    fun should_not_have_book_fragment() {
        Assert.assertNull(activity.supportFragmentManager.findFragmentByTag(BookFragment::class.java.name))
    }

    @Test
    fun should_have_book_fragment() {
        activity.onBookClicked(book)
        Assert.assertNotNull(activity.supportFragmentManager.findFragmentByTag(BookFragment::class.java.name))
    }
}

