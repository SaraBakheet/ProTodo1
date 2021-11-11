package com.example.protodo

import androidx.lifecycle.ViewModel
class ListViewModel : ViewModel() {


    val toDoRepo = ToDoRepository.get()
    val liveDataTask = toDoRepo.getAllTasks()
    fun addTask(task: ToDoData) {
        toDoRepo.addTask(task)

    }

}