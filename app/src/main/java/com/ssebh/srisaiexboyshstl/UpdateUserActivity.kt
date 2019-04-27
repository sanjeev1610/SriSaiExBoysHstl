package com.ssebh.srisaiexboyshstl

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ssebh.srisaiexboyshstl.entity.Address
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel
import kotlinx.android.synthetic.main.activity_update_user.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*

class UpdateUserActivity : AppCompatActivity() {

    var viwModelProviders: HostelViewModel? = null
    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_DOC_CAPTURE = 2

    //    var imageUri:Uri?= null
//    var docUri: Uri?= null
    var imageBitmap: Bitmap?=null
    var docBitmap: Bitmap? = null

    var imgFileName: File?=null
    var docileName: File?=null

    var imageOuputStram: FileOutputStream? = null
    var docOuputStram: FileOutputStream? = null

    var imagePath:String? = null
    var docPath:String? = null

    var users:Users_In_Rooms?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        viwModelProviders = ViewModelProviders.of(this).get(HostelViewModel::class.java)

        users = intent.getParcelableExtra("user")

        uiEdit()

    }

    private fun uiEdit() {
        add_update_user_item_name.setText(users!!.user_name)
        add_update_user_item_father_name.setText(users!!.user_father_name)
        add_update_user_item_phone.setText(users!!.user_phone.toString())
        add_update_user_item_roomno.setText(users!!.user_in_room_num.toString())
        add_update_user_item_joining.setText(users!!.joining_date)
        add_update_user_item_closing_date.setText(users!!.closing_date)
        add_update_user_item_district.setText(users!!.user_address.district)
        add_update_user_item_mandal.setText(users!!.user_address.mandal)
        add_update_user_item_village.setText(users!!.user_address.village)
        add_update_user_item_street.setText(users!!.user_address.street)
        add_update_user_item_houseno.setText(users!!.user_address.house_num)
        add_update_user_item_state.setText(users!!.user_address.state)
        add_update_user_item_pincode.setText(users!!.user_address.pincode.toString())

        imagePath = users!!.user_image_url
        docPath = users!!.user_doc_url

        add_update_user_item_join_picker.setOnClickListener {
            var listener = DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 ->
                add_update_user_item_joining.setText("$p3 - ${p2 + 1} - $p1")
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
        add_update_user_item_closing_picker.setOnClickListener {
            var listener = DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 ->
                add_update_user_item_closing_date.setText("$p3 - ${p2 + 1} - $p1")
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

        add_update_user_item_camera_imageurl.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE)

        }
        add_update_user_item_camera_docurl.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager)!=null){
                startActivityForResult(intent,REQUEST_DOC_CAPTURE)
            }


        }


        update_user_update.setOnClickListener {
            if (add_update_user_item_name.text.toString() == "" &&
                add_update_user_item_father_name.text.toString() == "" && add_update_user_item_phone.text.toString() == ""
                && add_update_user_item_roomno.text.toString() == "" &&
                add_update_user_item_joining.text.toString() == "" && add_update_user_item_closing_date.text.toString() == ""
                && add_update_user_item_district.text.toString() == "" &&
                add_update_user_item_mandal.text.toString() == "" && add_update_user_item_village.text.toString() == ""
                && add_update_user_item_street.text.toString() == "" &&
                add_update_user_item_houseno.text.toString() == "" && add_update_user_item_state.text.toString() == ""
                && add_update_user_item_pincode.text.toString() == ""
            ) return@setOnClickListener


            viwModelProviders!!.updateUsersInRooms(
                Users_In_Rooms(users!!.user_id ,
                    add_update_user_item_name.text.toString()
                    ,add_update_user_item_father_name.text.toString(),add_update_user_item_phone.text.toString().toLong(),
                    Address(add_update_user_item_houseno.text.toString(),add_update_user_item_village.text.toString(),
                        add_update_user_item_street.text.toString(),add_update_user_item_mandal.text.toString(),
                        add_update_user_item_district.text.toString(),  add_update_user_item_state.text.toString(),
                        add_update_user_item_pincode.text.toString().toInt() ),
                    imagePath!!,docPath!!, add_update_user_item_joining.text.toString(), add_update_user_item_closing_date.text.toString(),
                    add_update_user_item_roomno.text.toString().toInt()
                )
            )
           finish()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode== Activity.RESULT_OK && data!=null){
//            imageUri = data.data as Uri
            imageBitmap = data.getParcelableExtra("data")


            var file = filesDir
            val fileName = "image_pic_${UUID.randomUUID()}.jpg"
            imgFileName = File(file, fileName)

            try {
                imageOuputStram = FileOutputStream(imgFileName)
                imageBitmap!!.compress(Bitmap.CompressFormat.JPEG,100,imageOuputStram)
                imageOuputStram!!.flush()
                imageOuputStram!!.close()

                imagePath = imgFileName!!.absolutePath
                Log.d("ImageFile", imgFileName!!.absolutePath)
            }catch (ex: Exception){
                Toast.makeText(this,"Error in Image Store", Toast.LENGTH_SHORT).show()
            }



        }else  if(requestCode==REQUEST_DOC_CAPTURE && resultCode== Activity.RESULT_OK && data!=null){
//            docUri = data.data as Uri
            docBitmap = data.getParcelableExtra("data")


            var file = filesDir
            val fileName = "image_doc_${UUID.randomUUID()}.jpg"
            docileName = File(file, fileName)

            try {
                docOuputStram = FileOutputStream(docileName)
                docBitmap!!.compress(Bitmap.CompressFormat.JPEG,100,docOuputStram)
                docOuputStram!!.flush()
                docOuputStram!!.close()

                docPath = docileName!!.absolutePath
                Log.d("ImageFile", docileName!!.absolutePath)
            }catch (ex: Exception){
                Toast.makeText(this,"Error in Image Store", Toast.LENGTH_SHORT).show()
            }

        }

    }



}







































