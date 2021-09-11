package com.android.boilerplate.di.more

import com.android.boilerplate.model.repository.more.MoreRepository
import com.android.boilerplate.model.repository.more.MoreRepositoryImp
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
abstract class MoreModule {

    @Singleton
    @Binds
    abstract fun bindMoreRepository(repositoryImp: MoreRepositoryImp)
            : MoreRepository
}