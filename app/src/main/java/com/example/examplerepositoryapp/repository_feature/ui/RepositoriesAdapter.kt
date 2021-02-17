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


class RepositoriesAdapter(private val onItemSelected: (Repository) -> Unit) : ListAdapter<RepositoryListViewModel, RepositoriesAdapter.RepositoriesViewHolder>(DiffImpl()) {



    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RepositoryListViewModelItem -> ITEM_VIEW
            else -> EMPTY_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            ITEM_VIEW -> {
                val view = inflater.inflate(R.layout.item_repository, parent, false)
                RepositoriesViewHolderItem(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_repository_empty, parent, false)
                RepositoriesViewHolderEmpty(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val item = getItem(position)
        if(holder is RepositoriesViewHolderItem) {
            val repositoriesViewHolderItem = item as RepositoryListViewModelItem
            holder.repositoryName.text = repositoriesViewHolderItem.repository.name
            holder.repositoryDescription.text = repositoriesViewHolderItem.repository.description
            holder.itemView.setOnClickListener { onItemSelected.invoke(repositoriesViewHolderItem.repository) }
        }
    }

    abstract class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class RepositoriesViewHolderItem(itemView: View): RepositoriesViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.item_name)
        val repositoryDescription: TextView = itemView.findViewById(R.id.item_description)
    }

    class RepositoriesViewHolderEmpty(itemView: View): RepositoriesViewHolder(itemView)

    private class DiffImpl : DiffUtil.ItemCallback<RepositoryListViewModel>() {
        override fun areItemsTheSame(
            oldItem: RepositoryListViewModel,
            newItem: RepositoryListViewModel
        ): Boolean {
            return when(oldItem) {
                is RepositoryListViewModelItem -> oldItem == (newItem as? RepositoryListViewModelItem)?.repository?.id ?: false
                is RepositoryListViewModelEmpty -> oldItem == (newItem as? RepositoryListViewModelEmpty) ?: false
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: RepositoryListViewModel,
            newItem: RepositoryListViewModel
        ): Boolean {
            return when(oldItem) {
                is RepositoryListViewModelItem -> areItemsTheSame(oldItem, newItem)
                is RepositoryListViewModelEmpty -> areItemsTheSame(oldItem, newItem)
                else -> false
            }
        }

    }

    companion object {
        private const val ITEM_VIEW = 1
        private const val EMPTY_VIEW = 2
    }

}

abstract class RepositoryListViewModel

class RepositoryListViewModelItem(
    val repository: Repository
): RepositoryListViewModel()

class RepositoryListViewModelEmpty: RepositoryListViewModel()

