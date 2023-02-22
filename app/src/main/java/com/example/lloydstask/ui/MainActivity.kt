package com.example.lloydstask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.lloydstask.R
import com.example.lloydstask.databinding.ActivityMainBinding
import com.example.lloydstask.viewmodel.DogsViewModel
import com.example.lloydstask.viewmodel.MainActivityUiState
import com.example.lloydstask.wrapper.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var binding: ActivityMainBinding

    private val dogsViewModel by viewModels<DogsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        observeState()

        binding.btnRefresh.setOnClickListener {
            dogsViewModel.fetchDogsData()
        }
    }

    private fun observeState() {
        dogsViewModel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                handleState(state)
            }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: MainActivityUiState) {
        when (state) {
            is MainActivityUiState.Init -> { /* Do Nothing */ }
            is MainActivityUiState.Success -> showImage(state.dogsUrlModel.imageUrl)
            is MainActivityUiState.Error -> showErrorToast(state.errorMessage)
        }
    }

    private fun showImage(imageUrl: String?) {
        imageUrl?.let {
            imageLoader.loadImage(it, R.drawable.circular_progress_bar, binding.imageView)
        }
    }

    private fun showErrorToast(message: String?) {
        val errorMessage = message ?: getString(R.string.default_error_message)
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}