package com.example.userproject.main_retrofit

import retrofit2.Call
import retrofit2.http.GET

interface MainRetrofitService {
    @GET("test.php")
    fun getUsers(
    ) : Call<List<User>>
}