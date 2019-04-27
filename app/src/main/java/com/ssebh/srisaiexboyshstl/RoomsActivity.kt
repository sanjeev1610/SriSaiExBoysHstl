package com.ssebh.srisaiexboyshstl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssebh.srisaiexboyshstl.adaptor.Room_In_Floor_Adaptor
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.entity.Rooms_In_Floor
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel
import kotlinx.android.synthetic.main.activity_rooms.*

class RoomsActivity : AppCompatActivity() {
    var viwModelProviders: HostelViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        viwModelProviders = ViewModelProviders.of(this).get(HostelViewModel::class.java)



        var floorId = intent.getIntExtra("floor_id", 0)
        var title = intent.getStringExtra("name")

        supportActionBar!!.title = title

        if(floorId!=null){
            Common.floorId = floorId
        }
        val adaptor = Room_In_Floor_Adaptor(this)
        rv_activity_room.layoutManager = LinearLayoutManager(this)
        rv_activity_room.setHasFixedSize(true)
        if(adaptor!=null) {
            rv_activity_room.adapter = adaptor
        }

        fab_activty_room.setOnClickListener {

            val alert = AlertDialog.Builder(this)



            val viw: View = LayoutInflater.from(this).inflate(R.layout.add_room_item, null, false)
            val roomNo = viw.findViewById<EditText>(R.id.et_add_room_item_number)
            val roomType = viw.findViewById<EditText>(R.id.et_add_room_item_type)
            val roomFloorId = viw.findViewById<EditText>(R.id.et_add_room_item_floor_id)
            val roomState = viw.findViewById<Spinner>(R.id.et_add_room_item_state)
            val roomBeds = viw.findViewById<EditText>(R.id.et_add_room_item_beds)
            roomFloorId.setText(Common.floorId.toString())
            roomFloorId.visibility = View.GONE

            alert.setView(viw)

            alert.setTitle("Add Room")
            alert.setIcon(R.drawable.ic_add_box_black_24dp)
            alert.setPositiveButton("Add") { dialog, which ->
                if (roomNo.text.toString() == "" && roomType.text.toString() == "" && roomBeds.text.toString() == "") return@setPositiveButton


                viwModelProviders!!.insertRoomInCat(Rooms_In_Floor(0,roomNo.text.toString().toInt(),
                    roomType.text.toString(),roomBeds.text.toString().toInt(),roomState.selectedItem.toString(),roomFloorId.text.toString().toInt()))
            }
            alert.setCancelable(false)
            alert.setNegativeButton("Cancel"){dialog, which ->
                dialog.cancel()
            }

            alert.show()

        }


        viwModelProviders!!.allRoomsByFloorId.observe(this, Observer {rooms->
            rooms.let {
                if(it!=null) {
                    adaptor.setAllRoomsInFloor(it)
                }

            }
        })

        viwModelProviders!!.allUsersByRoomNum.observe(this, Observer {users->
            users.let {
               adaptor.setC(it)

            }
        })


    }
}
