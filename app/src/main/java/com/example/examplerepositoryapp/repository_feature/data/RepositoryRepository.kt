package com.example.examplerepositoryapp.repository_feature.data

import com.example.examplerepositoryapp.repository_feature.domain.Repository
import com.example.examplerepositoryapp.retrofit.RetrofitClientInstance
import io.reactivex.rxjava3.core.Single

//I know this is a silly name but it's correct, it is a repo for repo's

interface RepositoryRepository {
    fun getRepositories(): Single<List<Repository>>
}

class RepositoryRepositoryImpl: RepositoryRepository {

    private val service: RepositoryService = RetrofitClientInstance.retrofitInstance!!.create(RepositoryService::class.java)

    override fun getRepositories(): Single<List<Repository>> {
        return service.getRepositories(query = "Rx").map {
            it.items.map { repository ->
                repository.fromDto()
            }
        }
    }
}