package com.example.android.politicalpreparedness.election

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.politicalpreparedness.database.ElectionDao
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.network.models.VoterInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VoterInfoViewModel(private val dataSource: ElectionDao) : ViewModel() {

    //TODO: Add live data to hold voter info

    //TODO: Add var and methods to populate voter info

    //TODO: Add var and methods to support loading URLs

    //TODO: Add var and methods to save and remove elections to local database
    //TODO: cont'd -- Populate initial state of save button to reflect proper action based on election saved status

    /**
     * Hint: The saved state can be accomplished in multiple ways. It is directly related to how elections are saved/removed from the database.
     */
    private val voterInfo: MutableLiveData<VoterInfoResponse> = MutableLiveData()

    private val isFollowed: MutableLiveData<Boolean> = MutableLiveData()

    fun getVoterInfo() = voterInfo

    fun isFollowed() = isFollowed

    suspend fun loadVoterInfo(address: String, electionId: Int) {
        withContext(Dispatchers.IO) {
            CivicsApi.retrofitService.voterInfo(address, electionId).execute().body()!!
        }.also {
            voterInfo.value = it
        }
    }

    suspend fun followElection(election: Election) {
        withContext(Dispatchers.IO) {
            val savedElection = dataSource.getById(election.id)
            if (savedElection != null) {
                dataSource.delete(election)
                false
            } else {
                dataSource.save(election)
                true
            }
        }.also {
            isFollowed.value = it
        }
    }

    suspend fun isFollowed(electionId: Int) {
        withContext(Dispatchers.IO) {
            dataSource.getById(electionId) != null
        }.also {
            isFollowed.value = it
        }
    }
}