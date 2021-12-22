package com.arun.tutorial

import com.arun.tutorial.model.user
import retrofit2.Call
import javax.inject.Inject

interface Repository{
    suspend fun getUsers():Call<List<user>>
}

class RepositoryImpl @Inject constructor(
    private val api: Api
): Repository {
    override suspend fun getUsers(): Call<List<user>> {
        return api.getUsers()
    }
}