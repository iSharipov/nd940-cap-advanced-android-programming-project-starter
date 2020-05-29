package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionClickListener
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElectionsFragment: Fragment() {

    private lateinit var binding: FragmentElectionBinding

    //TODO: Declare ViewModel
    private val viewModel: ElectionsViewModel by lazy {
        ViewModelProvider(this, ElectionsViewModelFactory(requireContext())).get(ElectionsViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO: Add ViewModel values and create ViewModel

        //TODO: Add binding values

        //TODO: Link elections to voter info

        //TODO: Initiate recycler adapters

        //TODO: Populate recycler adapters
        binding = FragmentElectionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUpcomingElections().observe(viewLifecycleOwner, Observer {
            binding.upcomingElections.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ElectionListAdapter(ElectionClickListener {
                    if (it.division.state.isNotEmpty() || it.division.district.isNotEmpty()) {
                        findNavController().navigate(ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(it.id, it.division))
                    } else {
                        Toast.makeText(context, "Only the state is available", Toast.LENGTH_SHORT).show()
                    }
                }).apply { submitList(it) }
            }
        })
        viewModel.getSavedElections().observe(viewLifecycleOwner, Observer {
            binding.savedElections.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ElectionListAdapter(ElectionClickListener {
                    if (it.division.state.isNotEmpty() || it.division.district.isNotEmpty()) {
                        findNavController().navigate(ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(it.id, it.division))
                    } else {
                        Toast.makeText(context, "Only the state is available", Toast.LENGTH_SHORT).show()
                    }
                }).apply { submitList(it) }
            }
        })
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.loadUpcomingElections()
            viewModel.loadSavedElections()
        }
    }

    //TODO: Refresh adapters when fragment loads

}