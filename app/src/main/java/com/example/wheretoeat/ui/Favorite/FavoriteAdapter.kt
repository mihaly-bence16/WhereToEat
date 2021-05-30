package com.example.wheretoeat.ui.Favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.data.Favorite.Favorite
import kotlinx.android.synthetic.main.favorite_view.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>(){

    private var favoriteList = emptyList<Favorite>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_view,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = favoriteList[position]
        holder.itemView.text_view_restaurant_name.text = currentItem.name
        holder.itemView.text_view_address.text = currentItem.address
        holder.itemView.text_view_price.text = currentItem.price.toString()
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun setData(favorite: List<Favorite>){
        this.favoriteList=favorite
        notifyDataSetChanged()
    }
}