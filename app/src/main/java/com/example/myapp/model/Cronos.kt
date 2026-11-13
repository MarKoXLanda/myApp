package com.example.myapp.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity (tableName = "cronos")
data class Cronos (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("crono")
    val crono: String
)