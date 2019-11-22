package com.foodapp.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Meal(
    val id: String,
    val meal: String,
    val thumb: String,
    val instructions: String,
    val area: String,
    val tags: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(meal)
        writeString(thumb)
        writeString(instructions)
        writeString(area)
        writeString(tags)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Meal> = object : Parcelable.Creator<Meal> {
            override fun createFromParcel(source: Parcel): Meal = Meal(source)
            override fun newArray(size: Int): Array<Meal?> = arrayOfNulls(size)
        }
    }
}