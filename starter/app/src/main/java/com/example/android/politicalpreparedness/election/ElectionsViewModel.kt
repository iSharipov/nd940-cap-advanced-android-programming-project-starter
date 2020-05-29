package com.example.android.politicalpreparedness.election

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.politicalpreparedness.database.ElectionDao
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.models.Election
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//TODO: Construct ViewModel and provide election datasource
class ElectionsViewModel(private val electionDao: ElectionDao) : ViewModel() {

    //TODO: Create live data val for upcoming elections

    //TODO: Create live data val for saved elections

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database

    //TODO: Create functions to navigate to saved or upcoming election voter info
    private val upcomingElections: MutableLiveData<List<Election>> = MutableLiveData()
    private val savedElections: MutableLiveData<List<Election>> = MutableLiveData()

    fun getUpcomingElections() = upcomingElections
    fun getSavedElections() = savedElections

    suspend fun loadUpcomingElections() {
        withContext(Dispatchers.IO) {
            CivicsApi.retrofitService.upcomingElections().execute().body()?.elections!!
        }.also {
            upcomingElections.value = it
        }
    }

    suspend fun loadSavedElections() {
        withContext(Dispatchers.IO) {
            electionDao.getAll()
        }.also {
            savedElections.value = it
        }
    }
}