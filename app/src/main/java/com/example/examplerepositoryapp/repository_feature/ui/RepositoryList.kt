package com.example.examplerepositoryapp.repository_feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.examplerepositoryapp.R

class RepositoryList : Fragment() {

    private val viewModel: RepositoryViewModel by activityViewModels()

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

    private fun navigateToDetails() {
        findNavController().navigate(R.id.repositoryDetails)
    }

    private fun setupAdapter() {
        val repositoriesAdapter = RepositoriesAdapter(
            onItemSelected =  { repository ->
                viewModel.setSelectedRepository(repository)
                navigateToDetails()
            }
        )
        val repositoryListView: RecyclerView? = view?.findViewById(R.id.repositories)
        repositoryListView?.let {
            it.adapter = repositoriesAdapter
        }
        viewModel.getRepositories().observe(viewLifecycleOwner, Observer {
            repositoriesAdapter.submitList(it)
        })
    }
}