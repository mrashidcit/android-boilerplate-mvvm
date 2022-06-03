package com.android.boilerplate.model.repository.settings

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import com.android.boilerplate.R
import com.android.boilerplate.base.model.repository.BaseRepositoryImp
import com.android.boilerplate.model.data.aide.Language
import com.android.boilerplate.model.data.local.preference.Preferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
class SettingsRepositoryImp @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val preferences: Preferences
) : SettingsRepository, BaseRepositoryImp(preferences) {

    private val languages = mutableListOf<Language>()

    private val languagesLiveData = MutableLiveData<List<Language>>()

    private fun parseLanguagesJsonFile() {
        val json = context.assets.open("languages.json").bufferedReader()
            .use { it.readText() }
        val type = object : TypeToken<List<Language>>() {}.type
        languages.addAll(Gson().fromJson(json, type))
    }

    override fun getNotification(): Boolean {
        return preferences.getBoolean(Preferences.KEY_NOTIFICATION)
    }

    override fun setNotification(notification: Boolean) {
        preferences.setBoolean(Preferences.KEY_NOTIFICATION, notification)
    }

    override fun getSelectedThemeIndex(): Int {
        return when (preferences.getInt(Preferences.KEY_THEME)) {
            AppCompatDelegate.MODE_NIGHT_NO -> 1
            AppCompatDelegate.MODE_NIGHT_YES -> 2
            else -> 0
        }
    }

    override fun getSelectedThemeName(): String {
        return when (preferences.getInt(Preferences.KEY_THEME)) {
            AppCompatDelegate.MODE_NIGHT_NO -> context.getString(R.string.light)
            AppCompatDelegate.MODE_NIGHT_YES -> context.getString(R.string.dark)
            else -> context.getString(R.string.system_default)
        }
    }

    override fun setTheme(theme: Int) {
        preferences.setInt(Preferences.KEY_THEME, theme)
    }

    override fun getLanguagesLiveData(): MutableLiveData<List<Language>> = languagesLiveData

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun getLanguages() {
        if (languages.isEmpty()) {
            parseLanguagesJsonFile()
        }
        if (languagesLiveData.value == null) {
            val lang = preferences.getString(Preferences.KEY_LANG)
            if (lang == null || lang == Preferences.KEY_DEFAULT) {
                languages[0].selected = true
            } else {
                languages.forEach { item ->
                    if (item.lang == lang) {
                        item.selected = true
                    }
                }
            }
            languagesLiveData.postValue(languages)
        }
    }

    override fun getSelectedLanguageName(): String {
        if (languages.isEmpty()) {
            parseLanguagesJsonFile()
        }
        if (languages.isNotEmpty()) {
            val lang = preferences.getString(Preferences.KEY_LANG)
            languages.forEach { item ->
                if (item.lang == lang)
                    return item.name
            }
        }
        return context.getString(R.string.system_default)
    }

    override fun isSameLanguageSelected(lang: Language): Boolean {
        languagesLiveData.value?.let {
            it.forEach { item ->
                if (item.selected && item.id == lang.id)
                    return true
            }
        }
        return false
    }

    override suspend fun setLanguage(lang: Language) {
        // save the selected language in preferences
        preferences.setString(Preferences.KEY_LANG, lang.lang)
        languagesLiveData.value?.let {
            it.forEach { item -> item.selected = false }
            it.forEach { item -> if (item.id == lang.id) item.selected = true }
            languagesLiveData.postValue(it)
        }
    }
}