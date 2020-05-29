package com.example.android.politicalpreparedness.election

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding
import com.example.android.politicalpreparedness.network.models.Election
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VoterInfoFragment : Fragment() {

    private lateinit var binding: FragmentVoterInfoBinding
    private val args: VoterInfoFragmentArgs by navArgs()

    private val viewModel: VoterInfoViewModel by lazy {
        ViewModelProvider(this, VoterInfoViewModelFactory(requireContext())).get(VoterInfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //TODO: Add ViewModel values and create ViewModel

        //TODO: Add binding values

        //TODO: Populate voter info -- hide views without provided data.
        /**
        Hint: You will need to ensure proper data is provided from previous fragment.
         */

        //TODO: Handle loading of URLs

        //TODO: Handle save button UI state
        //TODO: cont'd Handle save button clicks
        binding = FragmentVoterInfoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    //TODO: Create method to load URL intents
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel!!.getVoterInfo().observe(viewLifecycleOwner, Observer {
            binding.election = it.election
            binding.state = it.state!![0]
            binding.followElection = FollowElectionClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.followElection(it)
                }
            }
            binding.browseElection = BrowseElectionClickListener{
               val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse(it)
                startActivity(openUrl)
            }
        })
        binding.viewModel!!.isFollowed().observe(viewLifecycleOwner, Observer {
            binding.isFollowed = it
        })

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.loadVoterInfo(args.argDivision.division(), args.argElectionId)
            viewModel.isFollowed(args.argElectionId)
        }
    }
}

class FollowElectionClickListener(private val listener: (election: Election) -> Unit) {
    fun followElection(election: Election) = listener(election)
}

class BrowseElectionClickListener(private val listener: (url: String) -> Unit) {
    fun browseElection(url: String) = listener(url)
}