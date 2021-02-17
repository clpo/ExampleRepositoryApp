package com.example.examplerepositoryapp.repository_feature.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface RepositoryService {

    @GET("/search/repositories")
    fun getRepositories(
        @Header("accept") accept: String = "application/vnd.github.v3+json",
        @Query("q") query: String
    ): Single<RepositoryCollectionDto>

}