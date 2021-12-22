package com.arun.tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.arun.tutorial.model.user
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import retrofit2.await
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getUseCase: UseCase
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.textView)
        getUsers()
    }

    private fun getUsers() = lifecycleScope.launch(Dispatchers.IO){
        val result: List<user> = getUseCase().await().filter { it.id == 1  }
        withContext(Dispatchers.Main) {
            textView.text = result.toString()
        }
    }
}