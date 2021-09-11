package com.android.boilerplate.viewmodel.more

import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.repository.more.MoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
@HiltViewModel
class MoreViewModel @Inject constructor(private val repository: MoreRepository) :
    BaseViewModel()