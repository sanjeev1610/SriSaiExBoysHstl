package com.ssebh.srisaiexboyshstl.common

import java.text.SimpleDateFormat
import java.util.*

object Common {

    var floorId = 0
    var roomNum = 0
    var closing_date = ""
    var floor_id = 0
    var room_no = 0


    fun convertToDateString(yourmilliseconds:Long): String{
        val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm")
        return  sdf.format(Date(yourmilliseconds))

    }
    fun convertToDate(yourmilliseconds:Long): Date{
        return  Date(yourmilliseconds)

    }


}