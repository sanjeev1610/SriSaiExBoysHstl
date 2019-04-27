package com.ssebh.srisaiexboyshstl.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    var house_num:String="",
    var village:String  ="",
    var street:String = "",
    var mandal:String = "",
    var district:String = "",
    var state:String = "",
    var pincode:Int = 0


    ):Parcelable