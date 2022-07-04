package com.android.boilerplate.view.auth.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentOnBoardBinding
import com.android.boilerplate.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class OnBoardFragment : BaseFragment() {

    private lateinit var binding: FragmentOnBoardBinding

    private val viewModel: MainViewModel by viewModels()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_on_board,
                container,
                false
            )
            binding.listener = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnCreateFreeAccount.setOnClickListener {
                findNavController().navigate(
                    OnBoardFragmentDirections.actionDestOnBoardToDestSignUp()
                )
            }
            tvAlreadyHaveAccount.setOnClickListener {
                findNavController().navigate(
                    OnBoardFragmentDirections.actionDestOnBoardToDestSignIn()
                )
            }
            tvLogin.setOnClickListener {
                findNavController().navigate(
                    OnBoardFragmentDirections.actionDestOnBoardToDestSignIn()
                )
            }
        }
    }
}