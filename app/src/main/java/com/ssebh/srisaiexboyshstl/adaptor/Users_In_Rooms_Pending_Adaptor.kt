package com.ssebh.srisaiexboyshstl.adaptor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ssebh.srisaiexboyshstl.R
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms
import com.ssebh.srisaiexboyshstl.interfaces.ItemClickListner

class Users_In_Rooms_Pending_Adaptor (var context: Context) : RecyclerView.Adapter<Users_In_Rooms_Pending_Adaptor.UserViewHolder>() {
    private var allPendingUsersRooms: List<Users_In_Rooms> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viw = LayoutInflater.from(context).inflate(R.layout.pending_list_item, parent, false)

        return UserViewHolder(viw)
    }

    override fun getItemCount(): Int {

        return allPendingUsersRooms.size
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {


        holder.userName.text = allPendingUsersRooms[position].user_name
        holder.userRoomNo.text = allPendingUsersRooms[position].user_in_room_num.toString()
        holder.userPhone.text = allPendingUsersRooms[position].user_phone.toString()
        val phone = allPendingUsersRooms[position].user_phone.toString()

        holder.userCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            if (intent.resolveActivity(context.packageManager) != null) {
               context.startActivity(intent)
            }
        }


        holder.setItemClickListner(object : ItemClickListner {
            override fun onClick(view: View, pos: Int, isLongClick: Boolean?) {
                return
            }
        })
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var itemclicklistner: ItemClickListner? = null

        val userRoomNo: TextView = itemView.findViewById(R.id.tv_pending_list_room_no)
        val userPhone: TextView = itemView.findViewById(R.id.tv_pending_list_phone)
        val userName: TextView = itemView.findViewById(R.id.tv_pending_list_user_name)
        val userCall: Button = itemView.findViewById(R.id.btn_pending_call)

        init {
            itemView.setOnClickListener(this)
        }


        fun setItemClickListner(itemClickListner: ItemClickListner) {
            this.itemclicklistner = itemClickListner
        }

        override fun onClick(v: View?) {
            itemclicklistner!!.onClick(v!!, adapterPosition, false)

        }


    }

    fun setAllPendingUsersInRooms(usersPendingInrooms: List<Users_In_Rooms>) {
        allPendingUsersRooms = usersPendingInrooms
        notifyDataSetChanged()
    }
}