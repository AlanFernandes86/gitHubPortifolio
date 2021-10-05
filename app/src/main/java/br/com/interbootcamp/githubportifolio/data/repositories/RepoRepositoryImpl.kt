package br.com.interbootcamp.githubportifolio.data.repositories

import br.com.interbootcamp.githubportifolio.data.network.services.GitHubService
import kotlinx.coroutines.flow.flow


class RepoRepositoryImpl(private val service: GitHubService) : RepoRepository {

    override suspend fun listRepositories(user: String) = flow {
        val repoList = service.listRepositories(user)
        emit(repoList)
    }
}