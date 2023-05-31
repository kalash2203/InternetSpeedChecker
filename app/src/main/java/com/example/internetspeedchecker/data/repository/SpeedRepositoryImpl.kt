package com.example.internetspeedchecker.data.repository

import com.example.internetspeedchecker.data.local.db.SpeedDatabase
import com.example.internetspeedchecker.data.local.entity.SpeedEntity
import com.example.internetspeedchecker.domain.repository.SpeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpeedRepositoryImpl @Inject constructor(
    private val db: SpeedDatabase
) : SpeedRepository {

    override suspend fun insertSpeed(speed: SpeedEntity): Boolean {
        return try {
            db.getSpeedDao().insert(speed)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun getAvgDownLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getMeanDownLinkSpeed()
    }

    override suspend fun getAvgUpLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getMeanUpLinkSpeed()
    }

    override suspend fun getMaxDownLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getMaxDownLinkSpeed()
    }

    override suspend fun getMaxUpLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getMaxUpLinkSpeed()
    }

    override suspend fun getMinDownLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getMinDownLinkSpeed()
    }

    override suspend fun getMinUpLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getMinUpLinkSpeed()
    }

    override suspend fun getCurrDownLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getCurrentDownLinkSpeed()
    }

    override suspend fun getCurrUpLinkSpeed(): Flow<Int> {
        return db.getSpeedDao().getCurrentUpLinkSpeed()
    }

}