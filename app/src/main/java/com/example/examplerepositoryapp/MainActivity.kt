package com.example.examplerepositoryapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.examplerepositoryapp.repository_feature.ui.RepositoryViewModel


class MainActivity : AppCompatActivity(), Host {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: RepositoryViewModel by viewModels()
    }

    override fun openWebBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}

interface Host {
    fun openWebBrowser(url: String)
}