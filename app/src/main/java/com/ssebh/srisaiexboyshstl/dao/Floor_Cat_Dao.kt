package com.ssebh.srisaiexboyshstl.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ssebh.srisaiexboyshstl.entity.Floor_Cat


@Dao
interface Floor_Cat_Dao {

    @Query("SELECT * FROM Floor_Cat")
    fun getAllFloorCat():LiveData<List<Floor_Cat>>

    @Query("SELECT * FROM Floor_Cat WHERE floor_id IN (:floor_id)")
    fun getFloorCat(floor_id: Int): LiveData<Floor_Cat>

    @Insert
    fun insertFloor(vararg floorCat:Floor_Cat)

    @Update
    fun updateFloor(vararg floorCat: Floor_Cat)

    @Delete
    fun deleteFloor(vararg floorCat: Floor_Cat)


}