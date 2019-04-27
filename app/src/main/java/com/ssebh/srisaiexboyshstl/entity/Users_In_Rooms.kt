package com.ssebh.srisaiexboyshstl.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(foreignKeys = [

    ForeignKey(
        entity = Rooms_In_Floor::class,
        parentColumns = ["room_num"],
        childColumns = ["user_in_room_num"],
        onDelete = ForeignKey.CASCADE
    )

], indices = [Index(value = ["user_in_room_num"], unique = false)]
)
@Parcelize
data class Users_In_Rooms (
    @PrimaryKey(autoGenerate = true)
    var user_id:Int=0,
    var user_name:String = "",
    var user_father_name: String = "",
    var user_phone:Long = 0,
    @Embedded
    var user_address:Address,
    var user_image_url:String="",
    var user_doc_url:String="",
    var joining_date: String = "",
    var closing_date: String = "",
    var user_in_room_num:Int = 0


): Parcelable