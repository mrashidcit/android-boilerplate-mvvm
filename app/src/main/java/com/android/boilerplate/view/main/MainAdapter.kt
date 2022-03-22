package com.android.boilerplate.view.main

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseAdapter
import com.android.boilerplate.base.view.BaseViewHolder
import com.android.boilerplate.databinding.ItemLanguageBinding
import com.android.boilerplate.databinding.ItemUserBinding
import com.android.boilerplate.model.data.local.database.entities.User

/**
 * @author Abdul Rahman
 */
class MainAdapter(
    private val context: Context,
    private val listener: ((user: User) -> Unit)? = null
) : BaseAdapter<ViewDataBinding, User, BaseViewHolder<ViewDataBinding>>(UserDiffCallback()) {

    override fun getInflater(): LayoutInflater {
        return LayoutInflater.from(context)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_user
    }

    @Suppress("UNCHECKED_CAST")
    override fun createViewHolder(binding: ViewDataBinding): BaseViewHolder<ViewDataBinding> {
        return UserViewHolder(binding as ItemUserBinding) as BaseViewHolder<ViewDataBinding>
    }

    override fun itemViewType(position: Int): Int {
        return 1
    }

    override fun viewRecycled(holder: BaseViewHolder<ViewDataBinding>) {
        val viewHolder = holder as RecyclerView.ViewHolder
        if (viewHolder is UserViewHolder) {
            viewHolder.binding.apply {
                ivPicture.setImageDrawable(null)
                tvName.text = null
                tvEmail.text = null
                tvPhone.text = null
            }
        }
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        BaseViewHolder<ItemUserBinding>(binding) {

        override fun bind(position: Int) {
            binding.apply {
                val userToBind = getItem(position)
                user = userToBind
                executePendingBindings()
                itemView.setOnClickListener {
                    listener?.let {
                        it(userToBind)
                    }
                }
            }
        }
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}