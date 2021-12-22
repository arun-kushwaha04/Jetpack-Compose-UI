package com.arun.tutorial

import com.arun.tutorial.model.user
import retrofit2.Call
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke():Call<List<user>> = repository.getUsers()
}