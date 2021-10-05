package br.com.interbootcamp.githubportifolio.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.interbootcamp.githubportifolio.data.network.model.Repo
import br.com.interbootcamp.githubportifolio.domain.ListUserRepositoriesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val listUserRepositoriesUseCase: ListUserRepositoriesUseCase
) : ViewModel() {

    private val _repo = MutableLiveData<State>()
    val repo: LiveData<State> = _repo

    fun getRepoList(user: String) {
        viewModelScope.launch {
            listUserRepositoriesUseCase(user)
                .onStart {
                    _repo.postValue(State.Loading)
                }
                .catch {
                    val message = it.message?.trim()
                    if(message == "HTTP 404"){
                        _repo.postValue(State.NotFound)
                    }
                    else{
                        _repo.postValue(State.Error(it))
                    }
                }
                .collect {
                    _repo.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        object NotFound : State()
        data class Success(val list: List<Repo>) : State()
        data class Error(val error: Throwable) : State()
    }

}
