package fr.imtatlantique.launeau

import android.os.Parcel
import android.os.Parcelable

/**
 * Data class representing a book. It implements parcelable so that we can pass book between fragments and activites easily.
 */
data class Book(val isbn: String, val title: String, val price: String, val cover: String) : Parcelable {

    constructor(parcel: Parcel) : this(
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel) = Book(parcel)
        override fun newArray(size: Int) = arrayOfNulls<Book?>(size)
    }
}
