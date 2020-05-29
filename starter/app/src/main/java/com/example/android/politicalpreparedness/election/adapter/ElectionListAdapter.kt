package com.example.android.politicalpreparedness.election.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.ViewholderElectionBinding
import com.example.android.politicalpreparedness.network.models.Election

class ElectionListAdapter(private val electionClickListener: ElectionClickListener) :
    ListAdapter<Election, ElectionViewHolder>(ElectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        getItem(position).also {
            holder.viewholderElectionBinding.election = it
            holder.viewholderElectionBinding.onElectionClickListener = electionClickListener
            holder.viewholderElectionBinding.executePendingBindings()
        }
    }
    //TODO: Bind ViewHolder

    //TODO: Add companion object to inflate ViewHolder (from)
}

//TODO: Create ElectionViewHolder
class ElectionViewHolder(val viewholderElectionBinding: ViewholderElectionBinding) : RecyclerView.ViewHolder(viewholderElectionBinding.root) {
    companion object{
        fun from(parent: ViewGroup): ElectionViewHolder{
            return ElectionViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.viewholder_election, parent, false))
        }
    }
}

//TODO: Create ElectionDiffCallback
class ElectionDiffCallback: DiffUtil.ItemCallback<Election>() {
    override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
        return oldItem == newItem
    }
}

//TODO: Create ElectionListener
class ElectionClickListener(private val listener: (election: Election) -> Unit) {
    fun onItemClicked(election: Election) = listener(election)
}