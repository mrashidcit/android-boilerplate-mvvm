package com.android.boilerplate.view.settings.language

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseAdapter
import com.android.boilerplate.base.view.BaseViewHolder
import com.android.boilerplate.databinding.ItemLanguageBinding
import com.android.boilerplate.model.data.aide.Language

/**
 * @author Abdul Rahman
 */
class LanguagesAdapter(
    private val context: Context,
    private val listener: ((language: Language) -> Unit)? = null
) : BaseAdapter<ItemLanguageBinding, Language, LanguagesAdapter.LanguageViewHolder>(
    LanguageDiffCallback()
) {

    override fun getInflater(): LayoutInflater {
        return LayoutInflater.from(context)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_language
    }

    override fun createViewHolder(binding: ItemLanguageBinding)
            : BaseViewHolder<ItemLanguageBinding> {
        return LanguageViewHolder(binding)
    }

    inner class LanguageViewHolder(private val binding: ItemLanguageBinding) :
        BaseViewHolder<ItemLanguageBinding>(binding) {

        override fun bind(position: Int) {
            binding.apply {
                val languageToBind = getItem(position)
                language = languageToBind
                executePendingBindings()
                itemView.setOnClickListener {
                    listener?.let {
                        it(languageToBind)
                    }
                }
            }
        }
    }

    private class LanguageDiffCallback : DiffUtil.ItemCallback<Language>() {
        override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem == newItem
        }
    }
}