package com.android.boilerplate.model.repository.more

import com.android.boilerplate.base.model.repository.BaseRepositoryImp
import com.android.boilerplate.model.data.local.preference.Preferences
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
class MoreRepositoryImp @Inject constructor(private val preferences: Preferences) : MoreRepository,
    BaseRepositoryImp(preferences)