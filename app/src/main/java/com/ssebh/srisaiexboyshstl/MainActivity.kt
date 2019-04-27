package com.ssebh.srisaiexboyshstl

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssebh.srisaiexboyshstl.adaptor.Floor_Cat_Adaptor
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.entity.Floor_Cat
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        var viwModelProviders:HostelViewModel? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viwModelProviders = ViewModelProviders.of(this).get(HostelViewModel::class.java)

        val adaptor = Floor_Cat_Adaptor(this)
        rv_activity_main.layoutManager = LinearLayoutManager(this)
        rv_activity_main.setHasFixedSize(true)
        rv_activity_main.adapter = adaptor

        fab_activty_main.setOnClickListener {

            val alert = AlertDialog.Builder(this)



            val viw: View = LayoutInflater.from(this).inflate(R.layout.add_floor_item, null, false)
            val name = viw.findViewById<EditText>(R.id.et_add_floor_name)

            alert.setView(viw)

            alert.setTitle("Add Floor")
            alert.setIcon(R.drawable.ic_add_box_black_24dp)
            alert.setPositiveButton("Add") { dialog, which ->
                if (name.text.toString() == "" ) return@setPositiveButton


                viwModelProviders!!.insertFloorCat(Floor_Cat(0,name.text.toString()))
            }
            alert.setCancelable(false)
            alert.setNegativeButton("Cancel"){dialog, which ->
                dialog.cancel()
            }

            alert.show()

        }


        viwModelProviders!!.allFloors.observe(this, Observer {floor_cat->
            floor_cat.let {
                adaptor.setAllFloorCats(it)

            }
        })



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId == R.id.pending){
            openPendingFeeActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openPendingFeeActivity() {
        val alert = AlertDialog.Builder(this)



        val viw: View = LayoutInflater.from(this).inflate(R.layout.search_view, null, false)
        val dueDate = viw.findViewById<EditText>(R.id.et_pending_date)
        val btnDatepkr = viw.findViewById<Button>(R.id.btn_pending_date_picker)


        btnDatepkr.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                dueDate.setText("$dayOfMonth - ${month + 1} - $year")




            }

            var cal = Calendar.getInstance()
            var year = cal.get(Calendar.YEAR)
            var month = cal.get(Calendar.MONTH)
            var day = cal.get(Calendar.DAY_OF_MONTH)
            // context,OnDateSetListener,year,month,day
            var dpd = DatePickerDialog(
                this, listener,
                year, month, day
            )
            dpd.show()
        }

        alert.setView(viw)

        alert.setTitle("Search Dues ")
        alert.setIcon(R.drawable.abc_ic_search_api_material)
        alert.setPositiveButton("Search") { dialog, which ->
            if(dueDate.text.toString() == "") return@setPositiveButton

            Common.closing_date = dueDate.text.toString()

            startActivity(Intent(this,PendingActivity::class.java))
            finish()

        }
        alert.setCancelable(false)
        alert.setNegativeButton("Cancel"){dialog, which ->
            dialog.cancel()
        }

        alert.show()
    }
}
