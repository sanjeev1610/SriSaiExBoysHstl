package com.ssebh.srisaiexboyshstl.adaptor

import android.content.Context
import android.content.Intent
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
import com.ssebh.srisaiexboyshstl.MainActivity
import com.ssebh.srisaiexboyshstl.R
import com.ssebh.srisaiexboyshstl.RoomsActivity
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.entity.Floor_Cat
import com.ssebh.srisaiexboyshstl.interfaces.ItemClickListner
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel

class Floor_Cat_Adaptor(var context: MainActivity) : RecyclerView.Adapter<Floor_Cat_Adaptor.FloorCatViewHolder>() {

    private var allFloorCats:List<Floor_Cat> = emptyList()
    private var viewModelProvirs:HostelViewModel = ViewModelProviders.of(context).get(HostelViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FloorCatViewHolder {
        val viw = LayoutInflater.from(context).inflate(R.layout.floor_cat_item_list, parent, false)

        return FloorCatViewHolder(viw)
    }

    override fun getItemCount(): Int {

        return allFloorCats.size
    }


    override fun onBindViewHolder(holder: FloorCatViewHolder, position: Int) {
        holder.name.text = allFloorCats[position].floor_name
        holder.img.setImageResource(R.drawable.ic_toys_black_24dp)

        holder.btnUpdate.setOnClickListener {
            val alert = AlertDialog.Builder(context)



            val viw: View = LayoutInflater.from(context).inflate(R.layout.add_floor_item, null, false)
            val name = viw.findViewById<EditText>(R.id.et_add_floor_name)

            name.setText(allFloorCats[position].floor_name)
            alert.setView(viw)

            alert.setTitle("Update Floor")
            alert.setIcon(R.drawable.ic_add_box_black_24dp)
            alert.setPositiveButton("Update") { dialog, which ->
                if (name.text.toString() == "" ) return@setPositiveButton

                viewModelProvirs.updateFloorCat(Floor_Cat(allFloorCats[position].floor_id, name.text.toString()))


            }
            alert.setCancelable(false)
            alert.setNegativeButton("Cancel"){dialog, which ->
                dialog.cancel()
            }

            alert.show()

        }

        holder.btnDelete.setOnClickListener {
            viewModelProvirs.deleteFloorCat(Floor_Cat(allFloorCats[position].floor_id,allFloorCats[position].floor_name))
        }


        holder.setItemClickListner(object : ItemClickListner{
            override fun onClick(view: View, pos: Int, isLongClick: Boolean?) {
                val intent = Intent(context,RoomsActivity::class.java)
                Common.floorId = allFloorCats[position].floor_id
                intent.putExtra("floor_id",allFloorCats[position].floor_id)
                intent.putExtra("name",allFloorCats[position].floor_name)
                context.startActivity(intent)
            }
        })
    }


    inner class FloorCatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private var itemclicklistner: ItemClickListner? = null

        val name:TextView =  itemView.findViewById(R.id.tv_floor_item_name)
        val btnUpdate:Button = itemView.findViewById(R.id.btn_floor_item_update)
        val btnDelete:Button = itemView.findViewById(R.id.btn_floor_item_delete)
        val img:ImageView = itemView.findViewById(R.id.iv_floor_item)
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

     fun  setAllFloorCats(floorCat: List<Floor_Cat>){
        allFloorCats = floorCat
         notifyDataSetChanged()
    }
}