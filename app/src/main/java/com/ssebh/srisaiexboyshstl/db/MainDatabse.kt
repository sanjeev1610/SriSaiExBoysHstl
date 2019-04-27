package com.ssebh.srisaiexboyshstl.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ssebh.srisaiexboyshstl.dao.Floor_Cat_Dao
import com.ssebh.srisaiexboyshstl.dao.Rooms_In_Floor_Dao
import com.ssebh.srisaiexboyshstl.dao.Users_In_Rooms_Dao
import com.ssebh.srisaiexboyshstl.entity.Floor_Cat
import com.ssebh.srisaiexboyshstl.entity.Rooms_In_Floor
import com.ssebh.srisaiexboyshstl.entity.Users_In_Rooms
import kotlinx.coroutines.CoroutineScope


@Database(entities = [Floor_Cat::class, Rooms_In_Floor::class, Users_In_Rooms::class], version = 1)
abstract  class MainDatabase : RoomDatabase(){

    abstract fun floorCatDao(): Floor_Cat_Dao
    abstract fun roomsInFloorDao(): Rooms_In_Floor_Dao
    abstract fun usersInRoomsDao(): Users_In_Rooms_Dao

    companion object {
        private var Instance:MainDatabase? =null

        fun getMainDatabase(context: Context, scope: CoroutineScope? ) : MainDatabase{
            val temp= Instance

            if(temp!=null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    MainDatabase::class.java,
                    "Hostel_Db").build()


                Instance = instance
                return instance
            }
        }

    }


}