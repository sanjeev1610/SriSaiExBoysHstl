package com.ssebh.srisaiexboyshstl.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["floor_id"], unique = true)])
data class Floor_Cat (
@PrimaryKey(autoGenerate = true)
var floor_id:Int=0,
var floor_name:String = ""
)