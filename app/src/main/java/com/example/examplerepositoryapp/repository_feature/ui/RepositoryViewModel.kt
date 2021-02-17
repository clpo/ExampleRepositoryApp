package com.example.examplerepositoryapp.repository_feature.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examplerepositoryapp.repository_feature.data.RepositoryRepository
import com.example.examplerepositoryapp.repository_feature.data.RepositoryRepositoryImpl
import com.example.examplerepositoryapp.repository_feature.domain.Repository


class RepositoryViewModel : ViewModel() {

    //Should use dependencies injection here, such as Dagger2, but doesn't seem worth it for this small project
    private val repositoryRepository: RepositoryRepository = RepositoryRepositoryImpl()

    val repositories: MutableLiveData<List<Repository>> = MutableLiveData()
    val selectedRepository: MutableLiveData<Repository> = MutableLiveData()


    fun submitQuery(query: String) {
        if(query.isNotBlank()) {
            repositoryRepository.getRepositories(query).subscribe(
                { repoList -> repositories.postValue(repoList)},
                { error -> Log.e("callError", error.message ?: "") }
            )
        } else {
            repositories.postValue(emptyList())
        }
    }

    fun setSelectedRepository(repository: Repository) {
        selectedRepository.postValue(repository)
    }

}