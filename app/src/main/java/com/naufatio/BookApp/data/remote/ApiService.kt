package com.naufatio.BookApp.data.remote

import com.naufatio.BookApp.data.BooksResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes")
    fun bookBySearch(
        @Query("q")
        query: String
    ) : Flowable<List<BooksResponse>>

    @GET("volumes")
    fun bookBySearchWithSort(
        @Query("q") query: String,
        @Query("orderBy") orderBy: String,
    )
}