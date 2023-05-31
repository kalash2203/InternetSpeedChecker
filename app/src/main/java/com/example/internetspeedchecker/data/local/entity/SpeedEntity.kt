package com.example.internetspeedchecker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "speeds")
data class SpeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uplinkSpeed: Int,
    val downLinkSpeed:Int
)
/*
SpeedEntity is a table that stores the uplink & DownLink Speed inside it
 */