package com.example.lloydstask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lloydstask.databinding.FragmentMovieListBinding
import com.example.lloydstask.domain.model.MovieDomainItem
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.ui.MovieItemClickListener
import com.example.lloydstask.ui.adapters.MovieItemAdapter
import com.example.lloydstask.ui.viewholders.MovieItemViewHolder
import com.example.lloydstask.ui.wrapper.ImageLoader
import com.example.lloydstask.utils.Result
import com.example.lloydstask.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : BaseFragment(), MovieItemClickListener {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: RecyclerView.Adapter<MovieItemViewHolder>

    private val viewModel by viewModels<MovieListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovieList()
    }

    override fun onClick(movieDomainItem: MovieDomainItem) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
            movieDomainItem.id
        )
        findNavController().navigate(action)
    }

    override fun setupObserver() {
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieListState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(result: Result<MovieListDomainModel?>) {
        when (result) {
            is Result.Loading -> {
                showProgressBar(binding.progressBar)
            }
            is Result.Success -> {
                hideProgressBar(binding.progressBar)
                handleSuccess(result.data)
            }
            is Result.Error -> {
                hideProgressBar(binding.progressBar)
                Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleSuccess(listDomainModel: MovieListDomainModel?) {
        listDomainModel?.let {
            adapter = MovieItemAdapter(it.movieDomainItemList, imageLoader, this)
            binding.rvMovieList.adapter = adapter
            binding.rvMovieList.layoutManager = LinearLayoutManager(activity)
        }
    }
}