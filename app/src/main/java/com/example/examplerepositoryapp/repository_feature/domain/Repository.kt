package com.example.examplerepositoryapp.repository_feature.domain

import java.io.Serializable


class Repository(
    val id : Int,
    val name : String,
    val description : String
) : Serializable