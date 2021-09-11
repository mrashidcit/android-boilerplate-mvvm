package com.android.boilerplate.viewmodel.settings

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.aide.Language
import com.android.boilerplate.model.repository.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: SettingsRepository) :
    BaseViewModel() {

    val languages = repository.getLanguagesLiveData()

    fun getNotification(): Boolean = repository.getNotification()

    fun setNotification(notification: Boolean) = repository.setNotification(notification)

    fun getSelectedThemeIndex(): Int = repository.getSelectedThemeIndex()

    fun getSelectedThemeName(): String = repository.getSelectedThemeName()

    fun setTheme(theme: Int) = repository.setTheme(theme)

    fun getLanguages() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getLanguages()
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }

    fun getSelectedLanguageName(): String = repository.getSelectedLanguageName()

    fun isSameLanguageSelected(lang: Language): Boolean = repository.isSameLanguageSelected(lang)

    fun setLanguage(lang: Language) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.setLanguage(lang)
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }
}