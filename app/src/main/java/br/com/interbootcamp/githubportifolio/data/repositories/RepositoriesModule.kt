package br.com.interbootcamp.githubportifolio.data.repositories

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoriesModule {

    fun load(){
        loadKoinModules(repositoriesModule())
    }
    private fun repositoriesModule(): Module {
        return module {
            single<RepoRepository> { RepoRepositoryImpl(get()) }
        }
    }
}