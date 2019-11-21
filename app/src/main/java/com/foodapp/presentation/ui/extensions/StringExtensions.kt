package com.foodapp.presentation.ui.extensions


fun String.getFirstTextBeforeSpace(): String {
    return this.split(" ").getOrNull(0) ?: ""
}

fun String.getNextTextAfterSpace(): String {
    return this.safeSubstring(
        this.getFirstTextBeforeSpace().length,
        this.length
    ).trim()
}

fun String.hasNextTextAfterSpace(): Boolean {
    return this.trimStart()
        .getNextTextAfterSpace()
        .isNotEmpty()
}

fun String.safeSubstring(startIndex: Int, endIndex: Int): String {
    return if (!this.isEmpty() && startIndex >= 0 && this.length >= endIndex) {
        this.substring(startIndex, endIndex)
    } else ""
}

fun String.getFirstAndLastName(): String{
    val firstName = this.getFirstTextBeforeSpace()
        .plus(" ")

  return firstName.plus(
      this.getNextTextAfterSpace()
       .getFirstTextBeforeSpace()
  ).trim()

}