package com.android.boilerplate.model.repository.feedback

import android.content.Context
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
class FeedbackRepositoryImp @Inject constructor(@ApplicationContext private val context: Context) :
    FeedbackRepository {

    private val improveDesign = MutableLiveData<Boolean>(false)
    private val improveExperience = MutableLiveData<Boolean>(false)
    private val improveFunctionality = MutableLiveData<Boolean>(false)
    private val improvePerformance = MutableLiveData<Boolean>(false)

    override fun getImproveDesign(): MutableLiveData<Boolean> = improveDesign

    override fun getImproveExperience(): MutableLiveData<Boolean> = improveExperience

    override fun getImproveFunctionality(): MutableLiveData<Boolean> = improveFunctionality

    override fun getImprovePerformance(): MutableLiveData<Boolean> = improvePerformance

    override fun setImproveDesign(value: Boolean) = improveDesign.postValue(value)

    override fun setImproveExperience(value: Boolean) = improveExperience.postValue(value)

    override fun setImproveFunctionality(value: Boolean) = improveFunctionality.postValue(value)

    override fun setImprovePerformance(value: Boolean) = improvePerformance.postValue(value)
}