package com.ssebh.srisaiexboyshstl.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ssebh.srisaiexboyshstl.entity.Rooms_In_Floor

@Dao
interface Rooms_In_Floor_Dao {

    @Query("SELECT * FROM Rooms_In_Floor")
    fun getAllRooms(): LiveData<List<Rooms_In_Floor>>

    @Query("SELECT * FROM Rooms_In_Floor WHERE room_of_floor_id IN (:floor_id) ")
    fun getRoomsByFloorId(floor_id:Int): LiveData<List<Rooms_In_Floor>>

    @Insert
    fun insertRoom(vararg rooms: Rooms_In_Floor)

    @Update
    fun updateRoom(vararg rooms: Rooms_In_Floor)

    @Delete
    fun deleteRoom(vararg rooms: Rooms_In_Floor)
}