package com.example.userproject.main_retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainModel {
    var mOnUserDataFetchedListener : OnUserDataFetchedListener? = null
    var mUserData = ArrayList<User>()
    companion object{
        val TAG = "MainModel"
    }
    fun doRetrofit(){
        var retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://172.16.11.19:8080/").build()

        var mainService = retrofit.create(MainRetrofitService::class.java)

        mainService.getUsers().enqueue(object : Callback<List<User>>{
            override fun onFailure(p0: Call<List<User>>, p1: Throwable) {
                Log.d(TAG, "실패 ${p0}, $p1")
                mOnUserDataFetchedListener?.onFailed()
            }

            override fun onResponse(p0: Call<List<User>>, p1: Response<List<User>>) {
                Log.d(TAG, "성공")
                mUserData = p1.body() as ArrayList<User>
                mOnUserDataFetchedListener?.onFetched()
            }

        })
    }
    fun setOnUserDataFetchedListener(onUserDataFetchedListener: OnUserDataFetchedListener){
        mOnUserDataFetchedListener = onUserDataFetchedListener
    }
    interface OnUserDataFetchedListener{
        fun onFetched()
        fun onFailed()
    }
}