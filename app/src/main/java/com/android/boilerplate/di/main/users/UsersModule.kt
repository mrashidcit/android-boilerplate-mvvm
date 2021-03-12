package com.android.boilerplate.di.main.users

import com.android.boilerplate.model.repository.main.users.UsersRepository
import com.android.boilerplate.model.repository.main.users.UsersRepositoryImp
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
abstract class UsersModule {

    @ActivityScoped
    @Binds
    abstract fun bindUsersRepository(usersRepositoryImp: UsersRepositoryImp)
            : UsersRepository
}