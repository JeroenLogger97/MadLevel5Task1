package com.example.madlevel5task1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "noteTable")
data class Note (
    val title: String,
    val lastUpdated: Date,
    val text: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)