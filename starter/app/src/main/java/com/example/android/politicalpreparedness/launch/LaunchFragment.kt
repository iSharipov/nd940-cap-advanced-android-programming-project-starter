package com.example.android.politicalpreparedness.launch

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.databinding.FragmentLaunchBinding
import com.example.android.politicalpreparedness.network.Utils

class LaunchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentLaunchBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.representativeButton.setOnClickListener { navToRepresentatives() }
        binding.upcomingButton.setOnClickListener { navToElections() }

        return binding.root
    }

    private fun navToElections() {
        this.findNavController().navigate(LaunchFragmentDirections.actionLaunchFragmentToElectionsFragment())
    }

    private fun navToRepresentatives() {
        if(Utils.isNetworkAvailable(requireContext())){
            this.findNavController().navigate(LaunchFragmentDirections.actionLaunchFragmentToRepresentativeFragment())
        }else{
            Toast.makeText(requireContext(), "You’re offline. Check your connection.", Toast.LENGTH_SHORT).show()
        }
    }

}
