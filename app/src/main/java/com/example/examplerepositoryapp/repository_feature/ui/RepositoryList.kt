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
import java.util.concurrent.TimeUnit

class RepositoryList : Fragment() {

    private val viewModel: RepositoryViewModel by activityViewModels()
    lateinit var adapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupSearch()
    }

    private fun setupSearch() {
        view?.let {
            val searchBar = it.findViewById<SearchBar>(R.id.search_bar)
            searchBar.inputSubject
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribe { text ->
                    viewModel.submitQuery(text ?: "")
                }
        }
    }

    private fun navigateToDetails() {
        findNavController().navigate(R.id.repositoryDetails)
    }

    private fun setupAdapter() {
        adapter = RepositoriesAdapter(
            onItemSelected =  { repository ->
                viewModel.setSelectedRepository(repository)
                navigateToDetails()
            }
        )
        val repositoryListView: RecyclerView? = view?.findViewById(R.id.repositories)
        repositoryListView?.let {
            it.adapter = adapter
        }
        adapter.submitList(listOf(RepositoryListViewModelEmpty()))

        viewModel.repositories.observe(viewLifecycleOwner, Observer { repositories ->
            if(repositories.isNullOrEmpty()) {
                adapter.submitList(listOf(RepositoryListViewModelEmpty()))
            } else {
                adapter.submitList(repositories.map { repository -> RepositoryListViewModelItem(repository) })
            }
        })
    }
}