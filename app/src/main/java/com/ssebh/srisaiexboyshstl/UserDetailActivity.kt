package com.ssebh.srisaiexboyshstl

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val uri = intent.getStringExtra("uri")

        imageView_user_detail.setImageURI(Uri.parse(uri))
    }
}
