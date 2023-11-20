package com.example.apiproject.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "password") val password: String?
)