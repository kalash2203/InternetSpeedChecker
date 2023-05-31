package com.example.internetspeedchecker.domain.repository

import com.example.internetspeedchecker.data.local.entity.SpeedEntity
import kotlinx.coroutines.flow.Flow

interface SpeedRepository {

    suspend fun insertSpeed(speed: SpeedEntity) : Boolean
    suspend fun getAvgDownLinkSpeed() : Flow<Int>
    suspend fun getAvgUpLinkSpeed() : Flow<Int>
    suspend fun getMaxDownLinkSpeed() : Flow<Int>
    suspend fun getMaxUpLinkSpeed() : Flow<Int>
    suspend fun getMinDownLinkSpeed() : Flow<Int>
    suspend fun getMinUpLinkSpeed() : Flow<Int>
    suspend fun getCurrDownLinkSpeed() : Flow<Int>
    suspend fun getCurrUpLinkSpeed() : Flow<Int>

}