package com.example.protodo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ToDoData

    ( @PrimaryKey val id:UUID=UUID.randomUUID(),

      var title : String ="",
      var date: Date = Date(),
      var detail :String="",
      var duoDate:Date? = null,
      var isCompleted:Boolean= false)


