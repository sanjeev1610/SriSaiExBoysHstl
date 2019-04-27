package com.ssebh.srisaiexboyshstl.adaptor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ssebh.srisaiexboyshstl.R
import com.ssebh.srisaiexboyshstl.RoomsActivity
import com.ssebh.srisaiexboyshstl.UsersActivity
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.entity.Rooms_In_Floor
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms
import com.ssebh.srisaiexboyshstl.interfaces.ItemClickListner
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel

class Room_In_Floor_Adaptor(var context: RoomsActivity) : RecyclerView.Adapter<Room_In_Floor_Adaptor.RoomViewHolder>(){
    private var allRoomsInFloor:List<Rooms_In_Floor> = emptyList()

    private var viewModelProvirs: HostelViewModel = ViewModelProviders.of(context).get(HostelViewModel::class.java)


    private var c:List<Users_In_Rooms> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val viw = LayoutInflater.from(context).inflate(R.layout.room_item_list, parent, false)

        return RoomViewHolder(viw)
    }

    override fun getItemCount(): Int {

        return allRoomsInFloor.size
    }


    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {


        holder.roomNo.text = allRoomsInFloor[position].room_num.toString()
        holder.roomType.text = allRoomsInFloor[position].room_type

        val status = allRoomsInFloor[position].room_state

        if(status == "Available"){
            holder.roomStatus.text = "Not Fill"

        }else{
            holder.roomStatus.text = "Filled"

        }



        holder.roomBtnUpdate.setOnClickListener {


            val alert = AlertDialog.Builder(context)



            val viw: View = LayoutInflater.from(context).inflate(R.layout.add_room_item, null, false)
            val roomNo = viw.findViewById<EditText>(R.id.et_add_room_item_number)
            val roomType = viw.findViewById<EditText>(R.id.et_add_room_item_type)
            val roomFloorId = viw.findViewById<EditText>(R.id.et_add_room_item_floor_id)
            val roomState = viw.findViewById<Spinner>(R.id.et_add_room_item_state)
            val roomBeds = viw.findViewById<EditText>(R.id.et_add_room_item_beds)

            roomNo.isEnabled = false
            roomFloorId.visibility = View.GONE

            roomNo.setText(allRoomsInFloor[position].room_num.toString())
            roomType.setText(allRoomsInFloor[position].room_type)
            roomFloorId.setText(allRoomsInFloor[position].room_of_floor_id.toString())
            roomBeds.setText(allRoomsInFloor[position].room_beds.toString())
            if(allRoomsInFloor[position].room_state.equals("Available")){
                roomState.setSelection(0)
            }else{
                roomState.setSelection(1)

            }



            alert.setView(viw)

            alert.setTitle("Update Room")
            alert.setIcon(R.drawable.ic_add_box_black_24dp)
            alert.setPositiveButton("Update") { dialog, which ->
                if (roomNo.text.toString() == "" && roomType.text.toString() == "" && roomBeds.text.toString() == "") return@setPositiveButton


                viewModelProvirs.updateRoomsInCat(Rooms_In_Floor(allRoomsInFloor[position].room_id
                    ,roomNo.text.toString().toInt(),
                    roomType.text.toString(),roomBeds.text.toString().toInt(),roomState.selectedItem.toString(),roomFloorId.text.toString().toInt()))
            }
            alert.setCancelable(false)
            alert.setNegativeButton("Cancel"){dialog, which ->
                dialog.cancel()
            }

            alert.show()

        }

        holder.roomBtnDelete.setOnClickListener {
            viewModelProvirs.deleteRoomsInCat(Rooms_In_Floor(allRoomsInFloor[position].room_id))
        }


        holder.setItemClickListner(object : ItemClickListner {
            override fun onClick(view: View, pos: Int, isLongClick: Boolean?) {
                val intent = Intent(context,UsersActivity::class.java)
                Common.roomNum = allRoomsInFloor[position].room_num
                intent.putExtra("room_no",allRoomsInFloor[position].room_num)
                intent.putExtra("beds", allRoomsInFloor[position].room_beds)
                context.startActivity(intent)
            }
        })
    }


    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private var itemclicklistner: ItemClickListner? = null

        val roomNo:TextView = itemView.findViewById(R.id.tv_room_item_num)
        val roomType:TextView = itemView.findViewById(R.id.tv_room_item_type)
        val roomStatus:TextView =  itemView.findViewById(R.id.tv_room_item_state)
        val roomBtnUpdate:Button = itemView.findViewById(R.id.btn_room_item_update)
        val roomBtnDelete:Button = itemView.findViewById(R.id.btn_room_item_delete)
        init {

            itemView.setOnClickListener(this)
        }

        fun setItemClickListner(itemClickListner: ItemClickListner){
            this.itemclicklistner = itemClickListner
        }

        override fun onClick(v: View?) {
            itemclicklistner!!.onClick(v!!, adapterPosition, false)

        }


    }

    fun  setAllRoomsInFloor(roomsInFloor: List<Rooms_In_Floor>){
        allRoomsInFloor = roomsInFloor
        notifyDataSetChanged()
    }
    fun  setC(roomsInFloor: List<Users_In_Rooms>){
        c = roomsInFloor
        notifyDataSetChanged()
    }
}