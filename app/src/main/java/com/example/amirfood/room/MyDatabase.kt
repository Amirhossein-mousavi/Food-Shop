package com.example.amirfood.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Food::class] , version = 1 , exportSchema = false )
abstract class MyDatabase :RoomDatabase() {

    abstract val foodDao :FoodDao
    companion object {
        var database : MyDatabase? = null
        fun getDatabase (context: Context) :MyDatabase{
            if (database == null) {
                database = Room.databaseBuilder(context.applicationContext,
                MyDatabase::class.java,
                "myDatabase.db")
                    .allowMainThreadQueries()
                    .build()


            }
            return database!!
        }


    }


}