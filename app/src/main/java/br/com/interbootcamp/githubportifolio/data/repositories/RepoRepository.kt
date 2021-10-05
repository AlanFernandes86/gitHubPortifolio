package br.com.interbootcamp.githubportifolio.data.repositories


import br.com.interbootcamp.githubportifolio.data.network.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}