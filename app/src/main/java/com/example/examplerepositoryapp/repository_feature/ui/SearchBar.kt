package com.example.examplerepositoryapp.repository_feature.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.example.examplerepositoryapp.R
import io.reactivex.rxjava3.subjects.PublishSubject


class SearchBar
@JvmOverloads constructor(
context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    var inputSubject: PublishSubject<String> = PublishSubject.create()

    init {
        inflate(context, R.layout.search_bar, this)
        val queryField = findViewById<EditText>(R.id.search_query_field)
        queryField.addTextChangedListener(
            onTextChanged = { text, start, before, count ->
                inputSubject.onNext(text.toString())
            }
        )
    }



}