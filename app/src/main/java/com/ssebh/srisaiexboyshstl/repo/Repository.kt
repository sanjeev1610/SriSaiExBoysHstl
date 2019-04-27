package com.ssebh.srisaiexboyshstl.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.ssebh.srisaiexboyshstl.common.Common
import com.ssebh.srisaiexboyshstl.dao.Floor_Cat_Dao
import com.ssebh.srisaiexboyshstl.dao.Rooms_In_Floor_Dao
import com.ssebh.srisaiexboyshstl.dao.Users_In_Rooms_Dao
import com.ssebh.srisaiexboyshstl.entity.Floor_Cat
import com.ssebh.srisaiexboyshstl.entity.Rooms_In_Floor
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms

open class Repository(private val floor_Cat_Dao: Floor_Cat_Dao,
                      private val rooms_In_Floor_Dao: Rooms_In_Floor_Dao,
                      private val users_In_Rooms_Dao: Users_In_Rooms_Dao
                      ) {

/**
  *Floor_Cat Operations
 */
    //get
    val allFloorsCat:LiveData<List<Floor_Cat>> = floor_Cat_Dao.getAllFloorCat()

    val getFloorCat: LiveData<Floor_Cat> = floor_Cat_Dao.getFloorCat(Common.floor_id)

    //insert
    @WorkerThread
    suspend fun insertFloorCat(floor_Cat: Floor_Cat){
        floor_Cat_Dao.insertFloor(floor_Cat)
    }
    //update
    @WorkerThread
    suspend fun updateFloorCat(floor_Cat: Floor_Cat) {
        floor_Cat_Dao.updateFloor(floor_Cat)
    }
    //delete
    @WorkerThread
    suspend fun deleteFloorCat(floor_Cat: Floor_Cat){
        floor_Cat_Dao.deleteFloor(floor_Cat)
    }


/**
  *Rooms_In_Floor Operations
 */


    //get
    val allRoomsOfFloorsCat:LiveData<List<Rooms_In_Floor>> = rooms_In_Floor_Dao.getAllRooms()
    val allRoomsOfFloorsCatByFloorId:LiveData<List<Rooms_In_Floor>> = rooms_In_Floor_Dao.getRoomsByFloorId(Common.floorId)

    //insert
    @WorkerThread
    suspend fun insertRoom(rooms_In_Floor: Rooms_In_Floor){
        rooms_In_Floor_Dao.insertRoom(rooms_In_Floor)
    }
    //update
    @WorkerThread
    suspend fun updateRoom(rooms_In_Floor: Rooms_In_Floor) {
        rooms_In_Floor_Dao.updateRoom(rooms_In_Floor)
    }
    //delete
    @WorkerThread
    suspend fun deleteRoom(rooms_In_Floor: Rooms_In_Floor){
        rooms_In_Floor_Dao.deleteRoom(rooms_In_Floor)
    }


    /**
     * Users_In_Rooms Operations
     */
    //get
    val allUsersOfRoom:LiveData<List<Users_In_Rooms>> = users_In_Rooms_Dao.getAllUsers()

    val allUsersOfRoomByRoomNum:LiveData<List<Users_In_Rooms>> = users_In_Rooms_Dao.getUsersByRoomNum(Common.roomNum)
    val allUsersOfRoomByClosingDate:LiveData<List<Users_In_Rooms>> = users_In_Rooms_Dao.getUsersByClosingDate(Common.closing_date)
    //insert
    @WorkerThread
    suspend fun insertUser(users_In_Rooms: Users_In_Rooms){
        users_In_Rooms_Dao.insertUser(users_In_Rooms)
    }
    //update
    @WorkerThread
    suspend fun updateUser(users_In_Rooms: Users_In_Rooms) {
        users_In_Rooms_Dao.updateUser(users_In_Rooms)
    }
    //delete
    @WorkerThread
    suspend fun deleteuser(users_In_Rooms: Users_In_Rooms){
        users_In_Rooms_Dao.deleteUser(users_In_Rooms)
    }




}