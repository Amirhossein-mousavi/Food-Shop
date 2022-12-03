package com.example.amirfood.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_food")
data class Food (
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null ,
    val subject: String ,
    val urlImg: String ,
    val price: String ,
    val city: String ,
    val distance: String ,
    val rating: Int ,
    val countRating: Float

        )