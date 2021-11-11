
package com.example.protodo
import android.app.Application

class AppTask : Application() {
    override fun onCreate() {
        super.onCreate()
        ToDoRepository.initialize(this)
    }
}



