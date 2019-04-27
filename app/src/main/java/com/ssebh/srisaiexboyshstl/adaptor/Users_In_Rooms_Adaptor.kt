package com.ssebh.srisaiexboyshstl.adaptor

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.ssebh.srisaiexboyshstl.R
import com.ssebh.srisaiexboyshstl.UpdateUserActivity
import com.ssebh.srisaiexboyshstl.UserDetailActivity
import com.ssebh.srisaiexboyshstl.UsersActivity
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.entity.Address
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms
import com.ssebh.srisaiexboyshstl.interfaces.ItemClickListner
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel
import java.io.File
import java.util.*

class Users_In_Rooms_Adaptor(var context: UsersActivity) : RecyclerView.Adapter<Users_In_Rooms_Adaptor.UserViewHolder>(){
    private var allUsersRooms:List<Users_In_Rooms> = emptyList()
    private var viewModelProvirs: HostelViewModel = ViewModelProviders.of(context).get(HostelViewModel::class.java)
    private val REQUEST_IMAGE_CAPTURE = 88
    private val REQUEST_DOC_CAPTURE = 99
    private var activity:Activity

    init {
        activity = UsersActivity()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viw = LayoutInflater.from(context).inflate(R.layout.user_item_list, parent, false)

        return UserViewHolder(viw)
    }

    override fun getItemCount(): Int {

        return allUsersRooms.size
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {


        holder.userName.text = allUsersRooms[position].user_name
        holder.userAddress.text = allUsersRooms[position].user_address.village +" "+ allUsersRooms[position].user_address.street +" "+
                allUsersRooms[position].user_address.house_num +" "+  allUsersRooms[position].user_address.mandal + " "+ allUsersRooms[position].user_address.district +" "+
                allUsersRooms[position].user_address.state + " "+ allUsersRooms[position].user_address.pincode
        holder.userFather.text = allUsersRooms[position].user_father_name
        holder.userJoiningDate.text = allUsersRooms[position].joining_date
        holder.userClosingDate.text = allUsersRooms[position].closing_date
        holder.userRoomNo.text = allUsersRooms[position].user_in_room_num.toString()
        holder.userPhone.text = allUsersRooms[position].user_phone.toString()
        holder.userImage.setImageURI(Uri.parse(allUsersRooms[position].user_image_url))
        holder.userDocImg.setImageURI(Uri.parse(allUsersRooms[position].user_doc_url))

        holder.userImage.setOnClickListener {
            val intent = Intent(context,UserDetailActivity::class.java)
            intent.putExtra("uri",allUsersRooms[position].user_image_url)
            context.startActivity(intent)
        }

        holder.userDocImg.setOnClickListener {
            val intent = Intent(context,UserDetailActivity::class.java)
            intent.putExtra("uri",allUsersRooms[position].user_doc_url)
            context.startActivity(intent)
        }


        holder.userUpdate.setOnClickListener {
            val intent = Intent(context,UpdateUserActivity::class.java)
            intent.putExtra("user",allUsersRooms[position])
            context.startActivity(intent)
        }



        holder.userDelete.setOnClickListener {

            viewModelProvirs.deleteUsersInRooms(Users_In_Rooms(allUsersRooms[position].user_id,"","",0,
                Address("","","","","","",0),"","","","",0
            ))
        }


        holder.setItemClickListner(object : ItemClickListner {
            override fun onClick(view: View, pos: Int, isLongClick: Boolean?) {
                return
            }
        })
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private var itemclicklistner: ItemClickListner? = null

        val userImage:ImageView = itemView.findViewById(R.id.iv_user_item_image)
        val userRoomNo:TextView = itemView.findViewById(R.id.tv_user_item_room_num)
        val userName:TextView = itemView.findViewById(R.id.tv_user_item_name)
        val userFather:TextView = itemView.findViewById(R.id.tv_user_item_father)
        val userPhone:TextView = itemView.findViewById(R.id.tv_user_item_phone)
        val userAddress:TextView = itemView.findViewById(R.id.tv_user_item_address)
        val userJoiningDate:TextView = itemView.findViewById(R.id.tv_user_item_room_Joining)
        val userClosingDate:TextView = itemView.findViewById(R.id.tv_user_item_room_closing)
        val userUpdate:Button = itemView.findViewById(R.id.btn_user_item_update)
        val userDelete:Button = itemView.findViewById(R.id.btn_user_item_delete)
        val userDocImg:ImageView = itemView.findViewById(R.id.iv_user_item_image_doc)

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

    fun  setAllUsersInRooms(usersInrooms: List<Users_In_Rooms>){
        allUsersRooms = usersInrooms
        notifyDataSetChanged()
    }



}