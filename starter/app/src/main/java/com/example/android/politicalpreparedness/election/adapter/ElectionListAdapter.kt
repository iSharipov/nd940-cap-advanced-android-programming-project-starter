package com.example.android.politicalpreparedness.election.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.ViewholderElectionBinding
import com.example.android.politicalpreparedness.network.models.Election
import kotlinx.android.synthetic.main.activity_main.*

class ElectionListAdapter(private val clickListener: ElectionListener): ListAdapter<Election, ElectionViewHolder>(ElectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    //TODO: Bind ViewHolder

    //TODO: Add companion object to inflate ViewHolder (from)
}

//TODO: Create ElectionViewHolder
class ElectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    companion object{
        fun from(parent: ViewGroup): ElectionViewHolder{
            return ElectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false))
        }
    }
}

//TODO: Create ElectionDiffCallback
class ElectionDiffCallback: DiffUtil.ItemCallback<Election>() {
    override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
        TODO("Not yet implemented")
    }
}

//TODO: Create ElectionListener
class ElectionListener: OnClickListener {
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}