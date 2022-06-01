package com.naufatio.BookApp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BooksResponse(
	@field:SerializedName("items")
	val items: List<ItemsItem>? = null
): Parcelable

@Parcelize
data class ItemsItem(

	@field:SerializedName("searchInfo")
	val searchInfo: SearchInfo? = null,

	@field:SerializedName("volumeInfo")
	val volumeInfo: VolumeInfo? = null,

	@field:SerializedName("id")
	val id: String? = null,

): Parcelable

@Parcelize
data class ImageLinks(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,
): Parcelable

@Parcelize
data class SearchInfo(

	@field:SerializedName("textSnippet")
	val textSnippet: String? = null

): Parcelable

@Parcelize
data class VolumeInfo(

	@field:SerializedName("pageCount")
	val pageCount: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("imageLinks")
	val imageLinks: ImageLinks? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("publishedDate")
	val publishedDate: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("averageRating")
	val averageRating: Int? = null,

	@field:SerializedName("maturityRating")
	val maturityRating: String? = null,

	@field:SerializedName("authors")
	val authors: List<String?>? = null,
): Parcelable
