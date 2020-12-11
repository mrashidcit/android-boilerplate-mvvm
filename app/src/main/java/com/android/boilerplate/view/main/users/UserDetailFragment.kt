package com.android.boilerplate.view.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentUserDetailBinding

/**
 * @author Abdul Rahman
 */
class UserDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentUserDetailBinding

    override fun getViewModel(): BaseViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_user_detail,
                container,
                false
            )
        }
        return binding.root
    }
}