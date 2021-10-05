package br.com.interbootcamp.githubportifolio.ui

import android.app.Application
import br.com.interbootcamp.githubportifolio.data.network.NetworkModule
import br.com.interbootcamp.githubportifolio.data.repositories.RepositoriesModule
import br.com.interbootcamp.githubportifolio.domain.DomainModule
import br.com.interbootcamp.githubportifolio.presentation.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
        }
        NetworkModule.load()
        RepositoriesModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}