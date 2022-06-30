package com.bitcode.webservices1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitcode.webservices1.UsersAdapter.UserViewHolder
import com.bitcode.webservices1.databinding.UserViewBinding
import com.bumptech.glide.Glide

class UsersAdapter(
    var users : ArrayList<User>
) : RecyclerView.Adapter<UserViewHolder>(){

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var userViewBinding = UserViewBinding.bind(itemView)

    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_view, null)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userViewBinding.txtUserId.text = "${users[position].id}"
        holder.userViewBinding.txtUsername.text = "${users[position].firstName} ${users[position].lastName}"

        Glide.with(holder.userViewBinding.root)
            .load(users[position].avatar)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .centerCrop()
            .into(holder.userViewBinding.imgUser)

    }

}