package com.naufatio.BookApp.data.remote

import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("volumes")
    fun bookSearchQuery(
        @Query("q") query: String,
        @Query("orderBy") orderBy: String = "newest"
    ): Flowable<BooksResponse>

    @GET("volumes/{id}")
    fun bookById(
        @Path("id") bookId: String
    ): Flowable<ItemsItem>

    @GET("volumes")
    fun bookBySearchWithSort(
        @Query("q") query: String,
        @Query("orderBy") category: String = "newest"
    ): Flowable<BooksResponse>

    @GET("volumes")
    fun bookRandomCategory(
        @Query("q") query: String,
        @Query("printType") printType: String = "books",
        @Query("orderBy") category: String = "newest"
    ): Flowable<BooksResponse>

    @GET("volumes")
    fun bookSearchByCategory(
        @Query("q") query: String,
        @Query("printType") printType: String = "books",
        @Query("orderBy") category: String = "newest"
    ): Flowable<BooksResponse>

}