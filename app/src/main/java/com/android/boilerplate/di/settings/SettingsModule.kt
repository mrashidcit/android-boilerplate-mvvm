package com.android.boilerplate.di.settings

import com.android.boilerplate.model.repository.settings.SettingsRepository
import com.android.boilerplate.model.repository.settings.SettingsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Abdul Rahman
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Singleton
    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImp: SettingsRepositoryImp)
            : SettingsRepository
}