package com.android.boilerplate.model.repository.main.settings

import androidx.lifecycle.LiveData
import com.android.boilerplate.model.data.aide.Language

/**
 * @author Abdul Rahman
 */
interface SettingsRepository {

    fun getLanguagesLiveData(): LiveData<List<Language>>

    suspend fun getLanguages()
}