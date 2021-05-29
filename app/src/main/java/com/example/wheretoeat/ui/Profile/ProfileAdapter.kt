package com.example.wheretoeat.ui.Profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.data.User.User
import com.example.wheretoeat.data.model.Restaurant
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.user_view.view.*
import kotlinx.android.synthetic.main.user_view.view.text_view_FN
import kotlinx.android.synthetic.main.user_view.view.text_view_LN

class ProfileAdapter (private val listener : OnItemClickListener)
    :  RecyclerView.Adapter<ProfileAdapter.MyViewHolder>(){

    private var userList= emptyList<User>()

    inner class MyViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position!=RecyclerView.NO_POSITION) {
                val current =userList[position]
            listener.onItemClick(current)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_view,parent,false))
    }

    override fun onBindViewHolder(holder: ProfileAdapter.MyViewHolder, position: Int) {
        val currentItem=userList[position]
        holder.itemView.text_view_user_id.text= currentItem.id.toString()
        holder.itemView.text_view_FN.text=currentItem.firstName
        holder.itemView.text_view_LN.text=currentItem.lastName
    }

    fun setData(user : List<User>){
        this.userList=user
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    interface OnItemClickListener{
        fun onItemClick(user: User)
    }
}