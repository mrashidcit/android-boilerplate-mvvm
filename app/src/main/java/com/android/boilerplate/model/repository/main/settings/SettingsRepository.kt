package com.android.boilerplate.model.repository.main.settings

import androidx.lifecycle.MutableLiveData
import com.android.boilerplate.model.data.aide.Language

/**
 * @author Abdul Rahman
 */
interface SettingsRepository {

    fun getSelectedThemeIndex(): Int

    fun getSelectedThemeName(): String

    fun setTheme(theme: Int)

    fun getLanguagesLiveData(): MutableLiveData<List<Language>>

    suspend fun getLanguages()

    fun getSelectedLanguageName(): String

    fun isSameLanguageSelected(lang: Language): Boolean

    suspend fun setLanguage(lang: Language)
}