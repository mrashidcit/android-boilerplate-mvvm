package com.android.boilerplate.viewmodel.main.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.aide.Language
import com.android.boilerplate.model.data.remote.request.UsersRequest
import com.android.boilerplate.model.repository.main.settings.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Abdul Rahman
 */
class SettingsViewModel @ViewModelInject constructor(private val repository: SettingsRepository) :
    BaseViewModel() {

    val languages = repository.getLanguagesLiveData()

    fun getLanguages() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoader(true)
                repository.getLanguages()
                showLoader(false)
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }
}