package com.example.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert
    suspend fun addTodo(todo: Todo1)

    @Delete
    suspend fun deleteTodo(todo: Todo1)

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Todo1>>


}
