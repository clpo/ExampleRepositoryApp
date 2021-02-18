package com.example.examplerepositoryapp.repository_feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.examplerepositoryapp.Host
import com.example.examplerepositoryapp.R
import java.time.format.DateTimeFormatter

//Displays details of the repository selected from RepositoryList
class RepositoryDetails : Fragment() {

    private val viewModel: RepositoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseViews(view = view)
    }

    private fun initialiseViews(view: View) {
        val noDataText = getString(R.string.no_data)
        //Would use data binding here instead
        viewModel.selectedRepository.observe(viewLifecycleOwner, { repository ->
            view.findViewById<TextView>(R.id.name).text = if(repository.name.isNotEmpty()) repository.name else noDataText
            view.findViewById<TextView>(R.id.description).text = if(repository.description.isNotEmpty()) repository.description else noDataText
            view.findViewById<TextView>(R.id.is_private).text = if(repository.isPrivate) getString(R.string.yes) else getString(R.string.no)
            view.findViewById<TextView>(R.id.link).text = if(repository.githubLink.isNotEmpty()) repository.githubLink else noDataText
            view.findViewById<TextView>(R.id.created_date).text = repository.createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            view.findViewById<TextView>(R.id.updated_date).text = repository.updatedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            view.findViewById<TextView>(R.id.language).text = if(repository.language.isNotEmpty()) repository.language else noDataText
            view.findViewById<TextView>(R.id.forks).text = repository.forksCount.toString()
            view.findViewById<TextView>(R.id.open_issues).text = repository.openIssuesCount.toString()

        })
        viewModel.repositoryReadme.observe(viewLifecycleOwner, { repositoryReadme ->
            if(repositoryReadme.webUrl.isNotBlank()) {
                val readMeButton = view.findViewById<Button>(R.id.readme_button)
                readMeButton.text = getString(R.string.view_readme)
                readMeButton.visibility = View.VISIBLE
                view.findViewById<TextView>(R.id.readme_title).visibility = View.VISIBLE
                readMeButton.setOnClickListener {
                    (activity as Host).openWebBrowser(repositoryReadme.webUrl)
                }
            }
        })
    }
}