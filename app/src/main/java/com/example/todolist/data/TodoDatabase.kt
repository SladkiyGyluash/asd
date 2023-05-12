package com.example.todolist.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Todo1::class]
)


abstract class TodoDatabase:RoomDatabase() {
    abstract fun TodoDao(): TodoDao
    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        fun  getDatabase(context: Context): TodoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database.db"

                ).build()
                INSTANCE  = instance
                return instance
            }
        }
    }
}