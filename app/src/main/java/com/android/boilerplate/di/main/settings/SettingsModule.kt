package com.android.boilerplate.di.main.settings

import com.android.boilerplate.model.repository.main.settings.SettingsRepository
import com.android.boilerplate.model.repository.main.settings.SettingsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * @author Abdul Rahman
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class SettingsModule {

    @ActivityScoped
    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImp: SettingsRepositoryImp)
            : SettingsRepository
}