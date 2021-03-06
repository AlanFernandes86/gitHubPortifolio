package br.com.interbootcamp.githubportifolio.data.network.services

import br.com.interbootcamp.githubportifolio.data.network.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user: String) : List<Repo>
}