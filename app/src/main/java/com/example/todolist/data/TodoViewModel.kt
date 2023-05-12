package com.example.todolist.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {

    val readAllData:LiveData<List<Todo1>>
    private val repository: TodoRepository

    init {
        val userDao = TodoDatabase.getDatabase(application).TodoDao()
        repository = TodoRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todo: Todo1){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo1){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTodo(todo)
        }
    }

}