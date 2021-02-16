package com.example.examplerepositoryapp.repository_feature.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examplerepositoryapp.R
import com.example.examplerepositoryapp.repository_feature.domain.Repository


class RepositoriesAdapter(private val onItemSelected: (Repository) -> Unit) : ListAdapter<Repository, RepositoriesAdapter.RepositoriesViewHolder>(DiffImpl()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_repository, parent, false)
        return RepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val repository = getItem(position)
        holder.repositoryName.text = repository.name
        holder.repositoryDescription.text = repository.description
        holder.itemView.setOnClickListener { onItemSelected.invoke(repository) }
    }

    class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.item_name)
        val repositoryDescription: TextView = itemView.findViewById(R.id.item_description)
    }

    private class DiffImpl : DiffUtil.ItemCallback<Repository>() {

        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id
    }

}

