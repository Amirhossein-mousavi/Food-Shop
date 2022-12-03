package com.example.amirfood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amirfood.room.Food
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>, private val itemClick: ItemClickListener) :
    RecyclerView.Adapter<FoodAdapter.FoodHolder>() {


    inner class FoodHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {


        val imgMain = itemView.findViewById<ImageView>(R.id.item_img_main)
        val subject = itemView.findViewById<TextView>(R.id.item_txt_subject)
        val city = itemView.findViewById<TextView>(R.id.item_txt_city)
        val distance = itemView.findViewById<TextView>(R.id.item_txt_distance)
        val price = itemView.findViewById<TextView>(R.id.item_txt_price)
        val rating = itemView.findViewById<RatingBar>(R.id.item_rating_main)
        val txtRating = itemView.findViewById<TextView>(R.id.item_txt_rating)

        fun bindViewHolder(position: Int) {
            subject.text = data[position].subject
            city.text = data[position].city
            distance.text = data[position].distance + "miles from you"
            price.text = "$" + data[position].price + " VIP"
            rating.rating = data[position].countRating
            txtRating.text = "(" + data[position].rating.toString() + "Ratings )"

            Glide.with(context)
                .load(data[position].urlImg)
                .placeholder(R.drawable.ic_baseline_cloud_download_24)
                .transform(RoundedCornersTransformation(8, 0))
                .into(imgMain)

            itemView.setOnClickListener {

                itemClick.setClick(data[adapterPosition] , adapterPosition)

            }
            itemView.setOnLongClickListener {
                itemClick.setLongClick(data[adapterPosition], adapterPosition)
                true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food, parent, false)
        return FoodHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bindViewHolder(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addFood(newData: Food) {
        data.add(0, newData)
        notifyItemInserted(0)
    }

    fun removFood(oldFood: Food, position: Int) {
        data.remove(oldFood)
        notifyItemRemoved(position)
    }
    fun editFood (food: Food, position: Int){
        data.set(position,food)
        notifyItemChanged(position)
    }
    fun setData (newData : ArrayList<Food>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    interface ItemClickListener {
        fun setClick(food: Food, position: Int)
        fun setLongClick(food: Food, position: Int)
    }

}