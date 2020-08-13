package com.example.userproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userproject.main_retrofit.MainModel
import com.example.userproject.main_retrofit.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var mainModel:MainModel
    var mUserData = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainModel = MainModel()
        mainModel.setOnUserDataFetchedListener(object : MainModel.OnUserDataFetchedListener{
            override fun onFetched() {
                mUserData = mainModel.mUserData
                user_textView.setText(mUserData.toString())
            }

            override fun onFailed() {

            }

        })
        mainModel.doRetrofit()
    }
}