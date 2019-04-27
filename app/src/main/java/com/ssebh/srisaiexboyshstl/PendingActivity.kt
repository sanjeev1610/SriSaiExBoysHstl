package com.ssebh.srisaiexboyshstl

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssebh.srisaiexboyshstl.adaptor.Users_In_Rooms_Pending_Adaptor
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel
import kotlinx.android.synthetic.main.activity_pending.*
import java.util.*

class PendingActivity : AppCompatActivity() {
        val REQ_CODE = 1
    var viwModelProviders: HostelViewModel? = null
   lateinit var adaptor:Users_In_Rooms_Pending_Adaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)
        viwModelProviders = ViewModelProviders.of(this).get(HostelViewModel::class.java)
        viwModelProviders = ViewModelProviders.of(this).get(HostelViewModel::class.java)
        viwModelProviders!!.allUsersByClosingDate.observe(this, Observer {users->
            users.let {
                adaptor.setAllPendingUsersInRooms(it)

            }
        })

        rv_pending_activity.layoutManager = LinearLayoutManager(this)
        rv_pending_activity.setHasFixedSize(true)
        adaptor = Users_In_Rooms_Pending_Adaptor(this)
        rv_pending_activity.layoutManager = LinearLayoutManager(this)
        rv_pending_activity.setHasFixedSize(true)
        adaptor.notifyDataSetChanged()
        rv_pending_activity.adapter = adaptor


    }



    override fun onResume() {

        super.onResume()
    }





}















