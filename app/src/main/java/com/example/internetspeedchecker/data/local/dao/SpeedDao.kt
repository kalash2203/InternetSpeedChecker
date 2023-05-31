package com.example.internetspeedchecker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.internetspeedchecker.data.local.entity.SpeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeedDao {
   /* Queries to get the average, max , current & min downLink Speed from the database
   It would Return data inside a flow
    */
    @Query("SELECT AVG(downLinkSpeed) FROM speeds")
    fun getMeanDownLinkSpeed(): Flow<Int>

    @Query("SELECT MAX(downLinkSpeed) FROM speeds")
    fun getMaxDownLinkSpeed(): Flow<Int>

    @Query("SELECT MIN(downLinkSpeed) FROM speeds")
    fun getMinDownLinkSpeed(): Flow<Int>

    @Query("SELECT downLinkSpeed FROM speeds ORDER BY id DESC LIMIT 1")
    fun getCurrentDownLinkSpeed() : Flow<Int>


    /* Queries to get the average, max, current & min upLink Speed from the database
   It would Return data inside a flow
    */

    @Query("SELECT AVG(uplinkSpeed) FROM speeds")
    fun getMeanUpLinkSpeed(): Flow<Int>

    @Query("SELECT MAX(uplinkSpeed) FROM speeds")
    fun getMaxUpLinkSpeed(): Flow<Int>

    @Query("SELECT MIN(uplinkSpeed) FROM speeds")
    fun getMinUpLinkSpeed(): Flow<Int>

    @Query("SELECT uplinkSpeed FROM speeds ORDER BY id DESC LIMIT 1")
    fun getCurrentUpLinkSpeed() : Flow<Int>

    /* Query to insert the current upLink & downLink Speed to the database
    */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(speed: SpeedEntity)
}