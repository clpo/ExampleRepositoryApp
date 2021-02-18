package com.example.examplerepositoryapp.repository_feature.domain

import java.time.LocalDateTime


class Repository(
    val id : Int,
    val name : String,
    val description : String,
    val isPrivate: Boolean,
    val githubLink: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val language: String,
    val forksCount: Int,
    val openIssuesCount: Int,
    val ownerLogin: String
)

class RepositoryReadme(
    val webUrl: String
)