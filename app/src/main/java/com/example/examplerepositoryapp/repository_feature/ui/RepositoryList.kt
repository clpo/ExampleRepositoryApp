package com.example.examplerepositoryapp.repository_feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.examplerepositoryapp.R
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

//Displays a list of Repository's based on query parameters
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
            viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
                searchBar.findViewById<ProgressBar>(R.id.progress_bar).visibility = if(isLoading) View.VISIBLE else View.GONE
            })
            searchBar.inputSubject
                .publish { ob ->
                    ob.buffer(ob.debounce(300, TimeUnit.MILLISECONDS))
                        .takeUntil(ob.ignoreElements().toObservable<String>()) }
                .subscribe { text ->
                    val textItem = (text as ArrayList<String?>).last()
                    viewModel.submitQuery(textItem ?: "")
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

        viewModel.repositories.observe(viewLifecycleOwner, { repositories ->
            if(repositories.isNullOrEmpty()) {
                adapter.submitList(listOf(RepositoryListViewModelEmpty()))
            } else {
                adapter.submitList(repositories.map { repository -> RepositoryListViewModelItem(repository) })
            }
        })
    }
}