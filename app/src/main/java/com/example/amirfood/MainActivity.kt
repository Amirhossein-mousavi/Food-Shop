package com.example.amirfood

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.amirfood.databinding.ActivityMainBinding
import com.example.amirfood.databinding.AddFoodActivityBinding
import com.example.amirfood.databinding.EditFoodActivityBinding
import com.example.amirfood.room.Food
import com.example.amirfood.room.FoodDao
import com.example.amirfood.room.MyDatabase
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), FoodAdapter.ItemClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter
    lateinit var foodDao: FoodDao
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        foodDao = MyDatabase.getDatabase(this).foodDao



        val sharedPreferences = getSharedPreferences("amirFood" , MODE_PRIVATE)
        if (sharedPreferences.getBoolean("firstRun" , true)) {
            firstRun()
            sharedPreferences.edit().putBoolean("firstRun" , false).apply()
        }
        showAllData()
        binding.addMain.setOnClickListener {
            addFood()
        }
        searchFood()

    }

    private fun firstRun() {
        val dataFood = arrayListOf(
            Food(
                subject = "humberger",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                price = "12",
                city = "Karaj",
                distance = "12",
                rating = 52,
                countRating = 3.5f),
            Food(
                subject = "sokhari",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                price = "19",
                city = "tehran",
                distance = "14",
                rating = 12,
                countRating = 1.5f),
            Food(
                subject = "lazania",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                price = "52",
                city = "isfahan",
                distance = "39",
                rating = 26,
                countRating = 5f),
            Food(
                subject = "pizza",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                price = "12",
                city = "Karaj",
                distance = "22",
                rating = 35,
                countRating = 4.5f),
            Food(
                subject = "soshi",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                price = "50",
                city = "Karaj",
                distance = "32",
                rating = 95,
                countRating = 2.75f),
            Food(
                subject = "fish",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                price = "62",
                city = "mazandaran",
                distance = "26",
                rating = 16,
                countRating = 3.25f),
            Food(
                subject = "chiken",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                price = "12",
                city = "tehran",
                distance = "62",
                rating = 14,
                countRating = 4.5f),
            Food(
                subject = "salad",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                price = "9",
                city = "mashhad",
                distance = "90",
                rating = 14,
                countRating = 4.5f),
            Food(
                subject = "pizza",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                price = "12",
                city = "Karaj",
                distance = "12",
                rating = 14,
                countRating = 4.5f),
            Food(
                subject = "pizza",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                price = "12",
                city = "Karaj",
                distance = "12",
                rating = 14,
                countRating = 4.5f),
            Food(
                subject = "pizza",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                price = "12",
                city = "Karaj",
                distance = "12",
                rating = 14,
                countRating = 4.5f),
            Food(
                subject = "pizza",
                urlImg = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                price = "12",
                city = "Karaj",
                distance = "12",
                rating = 14,
                countRating = 4.5f),

            )

        foodDao.insertAllFoods(dataFood)

    }
    private fun showAllData() {
        val foods = foodDao.getAllFoods()
        myAdapter = FoodAdapter(ArrayList(foods) , this)
        binding.recycleview.adapter = myAdapter
        binding.recycleview.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
    }
    private fun searchFood(){
        binding.txtInputSearch.addTextChangedListener {
            if (it == null) {
                val newData = foodDao.getAllFoods()
                myAdapter.setData(ArrayList(newData))
            } else {
                val newData = foodDao.searchFoods(it.toString())
                myAdapter.setData(ArrayList(newData))
            }
        }
    }

    private fun addFood (){
        val dialog = AlertDialog.Builder(this).create()
        val dialogView = AddFoodActivityBinding.inflate(layoutInflater)
        dialog.setView(dialogView.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogView.dialogBtnDone.setOnClickListener {
            if (
                dialogView.dialogFoodName.length() > 0 &&
                dialogView.dialogFoodCity.length() > 0 &&
                dialogView.dialogFoodPrice.length() > 0 &&
                dialogView.dialogFoodDistance.length() > 0
            )
            {
                val subject = dialogView.dialogFoodName.text.toString()
                val city = dialogView.dialogFoodCity.text.toString()
                val price = dialogView.dialogFoodPrice.text.toString()
                val distance = dialogView.dialogFoodDistance.text.toString()

                val urlImg = listOf<String>(
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg"

                )
                val randomImg = (0..11).random()
                val randomCountRating =(1..150).random()
                val min = 0f
                val max = 5f
                val randomRating =min + Random().nextFloat() * (max-min)


                val newFood = Food(
                     subject = subject ,urlImg = urlImg[randomImg] ,price = price ,city= city ,distance= distance ,rating= randomCountRating ,countRating= randomRating)
                foodDao.insertOrUpdateFood(newFood)
                myAdapter.addFood(newFood)
                binding.recycleview.scrollToPosition(0)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "مقادیر را کامل وارد کنید", Toast.LENGTH_SHORT).show()
            }

        }


    }
    override fun setClick(food: Food, position: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val dialogView = EditFoodActivityBinding.inflate(layoutInflater)
        dialog.setView(dialogView.root)
        dialog.setCancelable(true)
        dialog.show()
        dialogView.dialogFoodName.setText(food.subject)
        dialogView.dialogFoodCity.setText(food.city)
        dialogView.dialogFoodPrice.setText(food.price)
        dialogView.dialogFoodDistance.setText(food.distance)

        dialogView.dialogeditBtnDone.setOnClickListener {
            if (
                dialogView.dialogFoodName.length() > 0 &&
                dialogView.dialogFoodCity.length() > 0 &&
                dialogView.dialogFoodPrice.length() > 0 &&
                dialogView.dialogFoodDistance.length() > 0
            )
            {
                val subject = dialogView.dialogFoodName.text.toString()
                val city = dialogView.dialogFoodCity.text.toString()
                val price = dialogView.dialogFoodPrice.text.toString()
                val distance = dialogView.dialogFoodDistance.text.toString()


                val editFood = Food(
                  id=food.id, subject = subject ,urlImg = food.urlImg ,price = price ,city= city ,distance= distance ,rating= food.rating ,countRating= food.countRating)
                foodDao.insertOrUpdateFood(editFood)
                myAdapter.editFood(editFood , position)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "مقادیر را کامل وارد کنید", Toast.LENGTH_SHORT).show()
            }

        }
        dialogView.dialogeditBtnCancel.setOnClickListener {
            dialog.dismiss()
        }

    }

    override fun setLongClick(food: Food, position: Int) {
        val dialog = SweetAlertDialog(this , SweetAlertDialog.WARNING_TYPE)
        dialog.titleText = "Delete"
        dialog.contentText = "Are you sure about delete this item ?"
        dialog.confirmText = "yes"
        dialog.cancelText = "cancel"
        dialog.confirmButtonBackgroundColor = R.color.orange
        dialog.cancelButtonBackgroundColor = R.color.orange
        dialog.setConfirmClickListener {
            myAdapter.removFood(food , position)
            foodDao.deleteFood(food)
            dialog.dismiss()
        }
        dialog.setCancelClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }


}