package com.example.examplerepositoryapp.repository_feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.examplerepositoryapp.R
import com.example.examplerepositoryapp.repository_feature.domain.Repository

class RepositoryList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun navigateToDetails(repository: Repository) {
        val bundle: Bundle = Bundle().apply {
            this.putSerializable("repository", repository)
        }
        findNavController().navigate(R.id.repositoryDetails, bundle)
    }

    private fun setupAdapter() {
        val repositoriesAdapter : RepositoriesAdapter = RepositoriesAdapter(
            onItemSelected =  { repository -> navigateToDetails(repository) }
        )
        val repositoryListView : RecyclerView? = view?.findViewById<RecyclerView>(R.id.repositories)
        repositoryListView?.let {
            it.adapter = repositoriesAdapter
        }
        val repoList = listOf(
            Repository(id = 1234, name = "Repo 1", description = "This is a repo"),
            Repository(id = 1235, name = "Repo 2", description = "This is a repo"),
            Repository(id = 1236, name = "Repo 3", description = "This is a repo")
        )
        repositoriesAdapter.submitList(repoList)
    }
}