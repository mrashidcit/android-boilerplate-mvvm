package com.android.boilerplate.view.main.users

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.boilerplate.R
import com.android.boilerplate.databinding.ItemUserBinding
import com.android.boilerplate.model.data.local.database.entities.User

/**
 * @author Abdul Rahman
 */
class UsersAdapter(private val context: Context) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var items: List<User>? = null
    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    fun updateItems(items: List<User>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                inflater, R.layout.item_user, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            binding.apply {
                tvName.text = user.name?.title + ' ' + user.name?.first + ' ' + user.name?.last
                tvEmail.text = user.email
                tvGender.text = user.gender
                tvPhone.text = user.phone
            }
        }
    }
}