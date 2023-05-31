package com.example.internetspeedchecker.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.internetspeedchecker.data.local.dao.SpeedDao
import com.example.internetspeedchecker.data.local.entity.SpeedEntity


@Database(entities = [SpeedEntity::class], version = 1)
abstract class SpeedDatabase  : RoomDatabase(){
    //returns Dao interface
    abstract fun getSpeedDao(): SpeedDao
}