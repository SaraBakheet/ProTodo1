package com.example.protodo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoData")
    fun getAllTasks (): LiveData<List<ToDoData>>

    @Query("SELECT * FROM ToDoData WHERE id= (:id)")
    fun getTask(id: UUID): LiveData<ToDoData>

    @Update
    fun updateTasks (task: ToDoData)
    @Insert
    fun addTasks(task : ToDoData)


}

