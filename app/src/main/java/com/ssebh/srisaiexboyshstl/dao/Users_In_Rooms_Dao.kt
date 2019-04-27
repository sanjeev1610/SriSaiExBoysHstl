package com.ssebh.srisaiexboyshstl.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms

@Dao
interface Users_In_Rooms_Dao {


    @Query("SELECT * FROM Users_In_Rooms")
    fun getAllUsers(): LiveData<List<Users_In_Rooms>>


    @Query("SELECT * FROM Users_In_Rooms WHERE user_in_room_num IN (:roomNum) ")
    fun getUsersByRoomNum(roomNum:Int): LiveData<List<Users_In_Rooms>>

    @Query("SELECT * FROM Users_In_Rooms WHERE closing_date IN (:closing_date)")
    fun getUsersByClosingDate(closing_date: String): LiveData<List<Users_In_Rooms>>


    @Insert
    fun insertUser(vararg user: Users_In_Rooms)
    @Update
    fun updateUser(vararg user: Users_In_Rooms)

    @Delete
    fun deleteUser(vararg user: Users_In_Rooms)
}