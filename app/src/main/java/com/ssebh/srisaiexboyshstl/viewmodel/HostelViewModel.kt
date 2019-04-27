package com.ssebh.srisaiexboyshstl.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ssebh.srisaiexboyshstl.db.MainDatabase
import com.ssebh.srisaiexboyshstl.entity.Floor_Cat
import com.ssebh.srisaiexboyshstl.entity.Rooms_In_Floor
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms
import com.ssebh.srisaiexboyshstl.repo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HostelViewModel(application: Application): AndroidViewModel(application) {

    var parentJob = Job()

    private val coroutineContext:CoroutineContext
       get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val repo:Repository

    val allFloors: LiveData<List<Floor_Cat>>
    val oneFloorCat: LiveData<Floor_Cat>
    val allRooms: LiveData<List<Rooms_In_Floor>>
    val allRoomsByFloorId: LiveData<List<Rooms_In_Floor>>
    val allUsers: LiveData<List<Users_In_Rooms>>
    val allUsersByRoomNum: LiveData<List<Users_In_Rooms>>
    val allUsersByClosingDate: LiveData<List<Users_In_Rooms>>




    init {

        val floorDao = MainDatabase.getMainDatabase(application,scope).floorCatDao()
        val roomDao = MainDatabase.getMainDatabase(application,scope).roomsInFloorDao()
        val userDao = MainDatabase.getMainDatabase(application,scope).usersInRoomsDao()

        repo = Repository(floorDao,roomDao,userDao)

        allFloors = repo.allFloorsCat
        oneFloorCat = repo.getFloorCat
        allRooms = repo.allRoomsOfFloorsCat
        allRoomsByFloorId = repo.allRoomsOfFloorsCatByFloorId
        allUsers = repo.allUsersOfRoom
        allUsersByRoomNum = repo.allUsersOfRoomByRoomNum
        allUsersByClosingDate = repo.allUsersOfRoomByClosingDate

    }



//Insert
    fun insertFloorCat(floor_Cat: Floor_Cat) = scope.launch(Dispatchers.IO){  repo.insertFloorCat(floor_Cat)  }
    fun insertRoomInCat(rooms_In_Floor: Rooms_In_Floor) = scope.launch(Dispatchers.IO){ repo.insertRoom(rooms_In_Floor) }
    fun insertUserInRoom(users_In_Rooms: Users_In_Rooms) = scope.launch(Dispatchers.IO){ repo.insertUser(users_In_Rooms) }


//update
    fun updateFloorCat(floor_Cat: Floor_Cat) = scope.launch(Dispatchers.IO){ repo.updateFloorCat(floor_Cat) }
    fun updateRoomsInCat(rooms_In_Floor: Rooms_In_Floor) = scope.launch(Dispatchers.IO){ repo.updateRoom(rooms_In_Floor)}
    fun updateUsersInRooms(users_In_Rooms: Users_In_Rooms) = scope.launch(Dispatchers.IO){ repo.updateUser(users_In_Rooms)}


//delete
    fun deleteFloorCat(floor_Cat: Floor_Cat) = scope.launch(Dispatchers.IO){ repo.deleteFloorCat(floor_Cat) }
    fun deleteRoomsInCat(rooms_In_Floor: Rooms_In_Floor) = scope.launch(Dispatchers.IO){ repo.deleteRoom(rooms_In_Floor)}
    fun deleteUsersInRooms(users_In_Rooms: Users_In_Rooms) = scope.launch(Dispatchers.IO){ repo.deleteuser(users_In_Rooms)}

}









































