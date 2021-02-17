package com.example.examplerepositoryapp.repository_feature.data

import com.example.examplerepositoryapp.repository_feature.domain.Repository
import com.google.gson.annotations.SerializedName


class RepositoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
)

fun RepositoryDto.fromDto(): Repository = Repository(this.id, this.name, this.description)

class RepositoryCollectionDto(
    @SerializedName("items") val items: List<RepositoryDto>
)