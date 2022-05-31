package com.naufatio.BookApp.data.remote

import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes")
    fun bookBySearch(
        @Query("q")
        query: String
    )
}