package com.example.todolist.data

import androidx.lifecycle.LiveData

class TodoRepository (private val todoDao:TodoDao) {

    val readAllData :LiveData<List<Todo1>> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo1){
        todoDao.addTodo(todo)
    }


    suspend fun deleteTodo(todo: Todo1){
        todoDao.deleteTodo(todo)
    }
}