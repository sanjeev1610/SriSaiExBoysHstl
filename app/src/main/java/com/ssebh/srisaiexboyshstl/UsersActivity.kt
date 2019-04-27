package com.ssebh.srisaiexboyshstl

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.ssebh.srisaiexboyshstl.adaptor.Room_In_Floor_Adaptor
import com.ssebh.srisaiexboyshstl.adaptor.Users_In_Rooms_Adaptor
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.entity.Address
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms
import com.ssebh.srisaiexboyshstl.viewmodel.HostelViewModel
import kotlinx.android.synthetic.main.activity_users.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.net.URI
import java.util.*

class UsersActivity : AppCompatActivity() {
    var viwModelProviders: HostelViewModel? = null
     val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_DOC_CAPTURE = 2

//    var imageUri:Uri?= null
//    var docUri: Uri?= null
    var imageBitmap:Bitmap?=null
    var docBitmap:Bitmap? = null

    var imgFileName:File?=null
    var docileName:File?=null

    var imageOuputStram: FileOutputStream? = null
    var docOuputStram: FileOutputStream? = null

    var imagePath:String? = null
    var docPath:String? = null

    var beds:Int? = 0
    var tBeds:Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)


        viwModelProviders = ViewModelProviders.of(this).get(HostelViewModel::class.java)



        var roomNo = intent.getIntExtra("room_no", 0)
        beds = intent.getIntExtra("beds",0)

        if(roomNo!=null){
            Common.roomNum = roomNo
        }
        val adaptor = Users_In_Rooms_Adaptor(this)
        rv_user_activity.layoutManager = LinearLayoutManager(this)
        rv_user_activity.setHasFixedSize(true)
        if(adaptor!=null) {
            rv_user_activity.adapter = adaptor
        }

        fab_user_activity.setOnClickListener {

            val alert = AlertDialog.Builder(this)



            val itemView: View = LayoutInflater.from(this).inflate(R.layout.add_user_item, null, false)

            val userName: EditText = itemView.findViewById(R.id.add_user_item_name)
            val userFatherName: EditText = itemView.findViewById(R.id.add_user_item_father_name)
            val userPhone: EditText = itemView.findViewById(R.id.add_user_item_phone)
            val userVillage: EditText = itemView.findViewById(R.id.add_user_item_village)
            val userStreet: EditText = itemView.findViewById(R.id.add_user_item_street)
            val userHouseNo: EditText = itemView.findViewById(R.id.add_user_item_houseno)
            val userMandal: EditText = itemView.findViewById(R.id.add_user_item_mandal)
            val userDistrict: EditText = itemView.findViewById(R.id.add_user_item_district)
            val userState: EditText = itemView.findViewById(R.id.add_user_item_state)
            val userPincode: EditText = itemView.findViewById(R.id.add_user_item_pincode)
            val userJoinindDate: EditText = itemView.findViewById(R.id.add_user_item_joining)
            val closigDate: EditText = itemView.findViewById(R.id.add_user_item_closing_date)
            val joiningPicker: Button = itemView.findViewById(R.id.add_user_item_join_picker)
            val closingPicker: Button = itemView.findViewById(R.id.add_user_item_closing_picker)
            val imageButton: Button = itemView.findViewById(R.id.add_user_item_camera_imageurl)
            val docButton: Button = itemView.findViewById(R.id.add_user_item_camera_docurl)
            val userRoomNo: EditText = itemView.findViewById(R.id.add_user_item_roomno)
            userRoomNo.setText(Common.roomNum.toString())

            joiningPicker.setOnClickListener {
                var listener = DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 ->
                    userJoinindDate.setText("$p3 - ${p2 + 1} - $p1")
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
            closingPicker.setOnClickListener {
                var listener = DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 ->
                    closigDate.setText("$p3 - ${p2 + 1} - $p1")
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


            imageButton.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE)

            }
            docButton.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if(intent.resolveActivity(packageManager)!=null){
                    startActivityForResult(intent,REQUEST_DOC_CAPTURE)
                }


            }

            alert.setView(itemView)

            alert.setTitle("Add User")
            alert.setIcon(R.drawable.ic_add_box_black_24dp)
            alert.setPositiveButton("Add") { dialog, which ->
                if (userName.text.toString() == "" && userFatherName.text.toString() == "" && userPhone.text.toString() == ""
                    && userVillage.text.toString() == "" && userStreet.text.toString() == "" && userHouseNo.text.toString() == ""
                    && userMandal.text.toString() == "" && userDistrict.text.toString() == "" && userState.text.toString() == ""
                    && userPincode.text.toString() == "" && userJoinindDate.text.toString() == "" && closigDate.text.toString() == ""
                    && userRoomNo.text.toString() == ""
                ) return@setPositiveButton


                viwModelProviders!!.insertUserInRoom(
                   Users_In_Rooms(0,userName.text.toString(),userFatherName.text.toString(),userPhone.text.toString().toLong(),
                       Address(userHouseNo.text.toString(),userVillage.text.toString(),userStreet.text.toString(),userMandal.text.toString(),
                           userDistrict.text.toString(),  userState.text.toString(),  userPincode.text.toString().toInt() ),
                       imagePath!!,docPath!!, userJoinindDate.text.toString(), closigDate.text.toString(), userRoomNo.text.toString().toInt()
                       )
                )
            }
            alert.setCancelable(false)
            alert.setNegativeButton("Cancel"){dialog, which ->
                dialog.cancel()
            }

            alert.show()

        }


        viwModelProviders!!.allUsersByRoomNum.observe(this, Observer {users->
            users.let {
                if(it!=null) {
                    adaptor.setAllUsersInRooms(it)
                }

            }
        })


        viwModelProviders!!.allUsersByRoomNum.observe(this, Observer {users->
            users.let {

                tBeds = it.size
            }
        })

        if(beds!!.equals(tBeds)){
            supportActionBar!!.title =  "Room No - $roomNo - Filled"
        }else{
            supportActionBar!!.title = "Room No - $roomNo -Not Fill"

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==Activity.RESULT_OK && data!=null){
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
            }catch (ex:Exception){
                Toast.makeText(this@UsersActivity,"Error in Image Store",Toast.LENGTH_SHORT).show()
            }



        }else  if(requestCode==REQUEST_DOC_CAPTURE && resultCode==Activity.RESULT_OK && data!=null){
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
            }catch (ex:Exception){
                Toast.makeText(this@UsersActivity,"Error in Image Store",Toast.LENGTH_SHORT).show()
            }

        }

    }
}
