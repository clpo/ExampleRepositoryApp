package com.example.examplerepositoryapp.repository_feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.examplerepositoryapp.R
import com.example.examplerepositoryapp.repository_feature.domain.Repository

private const val ARG_KEY = "repository"

class RepositoryDetails : Fragment() {

    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repository = it.getSerializable(ARG_KEY) as Repository
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = repository.name
        val description = repository.description
        view.findViewById<TextView>(R.id.name).text = name
    }
}