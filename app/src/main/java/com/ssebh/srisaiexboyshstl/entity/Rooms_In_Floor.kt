package com.ssebh.srisaiexboyshstl.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(foreignKeys = [

ForeignKey(
    entity = Floor_Cat::class,
    parentColumns = ["floor_id"],
    childColumns = ["room_of_floor_id"],
    onDelete = ForeignKey.CASCADE
)

], indices = [Index(value = ["room_num"], unique = true), Index(value = ["room_of_floor_id"], unique = false)])
data class Rooms_In_Floor(
    @PrimaryKey(autoGenerate = true)
    var room_id:Int=0,
    var room_num:Int=0,
    var room_type:String = "",
    var room_beds:Int = 0,
    var room_state:String = "",
    var room_of_floor_id:Int = 0

)