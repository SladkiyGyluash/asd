package com.example.todolist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize




@Parcelize

@Entity(tableName = "todo_table")

data class Todo1(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title:String,
    val date:String,
    val isChecked: Boolean = false

) : Parcelable