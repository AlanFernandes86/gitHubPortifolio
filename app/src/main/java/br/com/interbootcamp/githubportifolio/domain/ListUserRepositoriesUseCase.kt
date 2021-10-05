package br.com.interbootcamp.githubportifolio.domain


import br.com.interbootcamp.githubportifolio.core.UseCase
import br.com.interbootcamp.githubportifolio.data.network.model.Repo
import br.com.interbootcamp.githubportifolio.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(
    private val repository: RepoRepository
) : UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}