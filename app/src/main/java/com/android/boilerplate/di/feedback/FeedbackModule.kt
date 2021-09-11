package com.android.boilerplate.di.feedback

import com.android.boilerplate.model.repository.feedback.FeedbackRepository
import com.android.boilerplate.model.repository.feedback.FeedbackRepositoryImp
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
abstract class FeedbackModule {

    @Singleton
    @Binds
    abstract fun bindFeedbackRepository(repositoryImp: FeedbackRepositoryImp)
            : FeedbackRepository
}