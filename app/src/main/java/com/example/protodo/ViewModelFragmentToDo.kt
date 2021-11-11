package com.example.protodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class ViewModelFragmentToDo : ViewModel(){
   private val toDoRepo = ToDoRepository.get()

   private val liveDataId = MutableLiveData<UUID>()

    val toDoDataLiveData:LiveData<ToDoData> =
        Transformations.switchMap(liveDataId) {

             toDoRepo.getTask(it)
        }

    fun saveUpdate(toDo: ToDoData){

        toDoRepo.updateTask(toDo)

    }



    fun loadToDo(id:UUID){
        liveDataId.value = id

    }


}
