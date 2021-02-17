package com.example.examplerepositoryapp.repository_feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.examplerepositoryapp.R
import java.time.format.DateTimeFormatter

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
        viewModel.selectedRepository.observe(viewLifecycleOwner, Observer { repository ->
            view.findViewById<TextView>(R.id.name).text = repository.name
            view.findViewById<TextView>(R.id.description).text = repository.description
            view.findViewById<TextView>(R.id.is_private).text = if(repository.isPrivate) getString(R.string.yes) else getString(R.string.no)
            view.findViewById<TextView>(R.id.link).text = repository.githubLink
            view.findViewById<TextView>(R.id.created_date).text = repository.createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            view.findViewById<TextView>(R.id.updated_date).text = repository.updatedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            view.findViewById<TextView>(R.id.language).text = repository.language
            view.findViewById<TextView>(R.id.forks).text = repository.forksCount.toString()
            view.findViewById<TextView>(R.id.open_issues).text = repository.openIssuesCount.toString()

        })
    }
}