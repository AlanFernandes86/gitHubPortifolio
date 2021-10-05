package br.com.interbootcamp.githubportifolio.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.interbootcamp.githubportifolio.R
import br.com.interbootcamp.githubportifolio.core.hideSoftKeyboard
import br.com.interbootcamp.githubportifolio.databinding.ActivityMainBinding
import br.com.interbootcamp.githubportifolio.presentation.MainViewModel
import br.com.interbootcamp.githubportifolio.ui.adapter.RepoListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.rvListRepo.adapter = adapter

        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.repo.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> {
                    binding.lpiMain.visibility = View.VISIBLE
                }
                MainViewModel.State.NotFound -> {
                    adapter.submitList(listOf())
                    binding.ivMessage.setImageResource(R.drawable.user_not_found)
                    binding.ivMessage.visibility = View.VISIBLE
                    binding.lpiMain.visibility = View.GONE
                }
                is MainViewModel.State.Error -> {
                    adapter.submitList(listOf())
                    binding.ivMessage.setImageResource(R.drawable.no_internet)
                    binding.ivMessage.visibility = View.VISIBLE
                    binding.lpiMain.visibility = View.GONE
                }
                is MainViewModel.State.Success -> {
                    binding.lpiMain.visibility = View.GONE
                    if(it.list.isNotEmpty()){
                        adapter.submitList(it.list)
                        binding.ivMessage.visibility = View.GONE
                    }
                    else{
                        binding.ivMessage.visibility = View.VISIBLE
                        binding.ivMessage.setImageResource(R.drawable.user_not_found)
                        adapter.submitList(listOf())
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.item_menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}