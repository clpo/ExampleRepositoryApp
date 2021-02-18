package com.example.examplerepositoryapp.repository_feature.data

import com.example.examplerepositoryapp.repository_feature.domain.Repository
import com.example.examplerepositoryapp.repository_feature.domain.RepositoryReadme
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


class RepositoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("private") val isPrivate: Boolean?,
    @SerializedName("html_url") val githubLink: String?,
    @SerializedName("created_at") val createdAt: LocalDateTime?,
    @SerializedName("updated_at") val updatedAt: LocalDateTime?,
    @SerializedName("language") val language: String?,
    @SerializedName("forks") val forksCount: Int?,
    @SerializedName("open_issues") val openIssuesCount: Int?,
    @SerializedName("owner") val owner: RepositoryOwnerDto?
)

class RepositoryOwnerDto(
    @SerializedName("login") val login: String?
)

class ReadmeDto(
    @SerializedName("html_url") val htmlUrl: String?
)

fun ReadmeDto.fromDto(): RepositoryReadme = RepositoryReadme(this.htmlUrl ?: "")

fun RepositoryDto.fromDto(): Repository =
    Repository(
        this.id,
        this.name ?: "",
        this.description ?: "",
        this.isPrivate ?: false,
        this.githubLink ?: "",
        this.createdAt ?: LocalDateTime.now(),
        this.updatedAt ?: LocalDateTime.now(),
        this.language ?: "",
        this.forksCount ?: 0,
        this.openIssuesCount ?: 0,
        this.owner?.login ?: ""
    )

class RepositoryCollectionDto(
    @SerializedName("items") val items: List<RepositoryDto>
)