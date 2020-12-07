package com.android.boilerplate.view.main

import android.os.Bundle
import android.widget.Toast
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseActivity
import com.android.boilerplate.base.viewmodel.BaseViewModel

class MainActivity : BaseActivity() {

    override fun getViewModel(): BaseViewModel? = null

    override fun hasConnectivity(connectivity: Boolean) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}