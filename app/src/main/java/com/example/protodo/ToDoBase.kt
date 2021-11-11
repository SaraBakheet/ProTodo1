package com.example.protodo
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters



@Database( entities = [ToDoData::class],version = 1)
@TypeConverters (ToDoTypeConverters::class) // to make it primitive data
abstract class ToDoBase  :RoomDatabase (){
    abstract fun taskdao(): ToDoDao


}
