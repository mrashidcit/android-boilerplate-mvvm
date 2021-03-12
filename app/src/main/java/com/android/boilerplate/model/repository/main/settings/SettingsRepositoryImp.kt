package com.android.boilerplate.model.repository.main.settings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.boilerplate.model.data.aide.Language
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
@ActivityScoped
class SettingsRepositoryImp @Inject constructor(
    @ApplicationContext
    private val context: Context
) : SettingsRepository {

    private val languages = MutableLiveData<List<Language>>()

    override fun getLanguagesLiveData(): LiveData<List<Language>> = languages

    override suspend fun getLanguages() {
        if (languages.value == null) {
            val tempLanguages = mutableListOf<Language>()
            tempLanguages.add(Language(1, "ar", "(Arabic) العربية"))
            tempLanguages.add(Language(2, "nl", "Nederlands (Dutch)"))
            tempLanguages.add(Language(3, "en", "English", true))
            languages.postValue(tempLanguages)
        }
    }
}