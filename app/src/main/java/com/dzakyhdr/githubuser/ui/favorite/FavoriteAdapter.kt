package com.dzakyhdr.githubuser.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzakyhdr.githubuser.R
import com.dzakyhdr.githubuser.data.local.UserEntity
import com.dzakyhdr.githubuser.databinding.ItemUserBinding

class FavoriteAdapter: ListAdapter<UserEntity, FavoriteAdapter.UserViewHolder>(DiffCallBack()) {


    class UserViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemUserBinding.bind(item)

        fun bind(user: UserEntity) {
            Glide.with(binding.root).load(user.avatarUrl).centerCrop().into(binding.avatars)
            binding.txtUsername.text = user.login
            binding.root.setOnClickListener {
                val username = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(user.login)
                it.findNavController().navigate(username)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val listUserPosition = getItem(position)
        holder.bind(listUserPosition)
    }


}
class DiffCallBack: DiffUtil.ItemCallback<UserEntity>(){
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }

}