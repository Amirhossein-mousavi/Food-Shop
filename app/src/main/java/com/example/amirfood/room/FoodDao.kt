package com.example.amirfood.room

import androidx.room.*

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateFood (food: Food)

    @Insert
    fun insertAllFoods (foods : ArrayList<Food>)

    @Delete
    fun deleteFood (food: Food)


    @Query("SELECT * FROM table_food")
    fun getAllFoods() : List<Food>

    @Query("SELECT * FROM table_food WHERE subject LIKE :search ||'%'")
    fun searchFoods (search :String) :List<Food>



}