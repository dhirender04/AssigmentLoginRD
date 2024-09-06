package com.example.assigmentloginrd.ui.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.assigmentloginrd.R
import com.example.assigmentloginrd.databinding.ItemUserBinding
import com.example.assigmentloginrd.db.User

class UserListAdapter(private val onDelete: (User) -> Unit) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val userList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position], onDelete)
    }

    fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }
    fun deleteUserAt(position: Int) {
        if (position in userList.indices) {
            val userToRemove = userList[position]
            userList.removeAt(position)
            notifyItemRemoved(position)
            onDelete(userToRemove)
        }
    }



    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onDelete: (User) -> Unit) {
            binding.apply {
                // Directly set the values instead of using data binding
                nameTextView.text = user.name
                emailTextView.text = user.email
                root.setOnLongClickListener {
                    onDelete(user)
                    true
                }
            }
        }
    }

}






