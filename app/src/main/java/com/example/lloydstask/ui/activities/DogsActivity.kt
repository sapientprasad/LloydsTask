package com.example.lloydstask.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lloydstask.R
import com.example.lloydstask.databinding.ActivityDogsBinding
import com.example.lloydstask.ui.wrapper.ImageLoader
import com.example.lloydstask.viewmodel.DogsViewModel
import com.example.lloydstask.viewmodel.MainActivityUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DogsActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var binding: ActivityDogsBinding

    private val dogsViewModel by viewModels<DogsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDogsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        observeState()

        binding.btnRefresh.setOnClickListener {
            dogsViewModel.fetchDogsData()
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dogsViewModel.state.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(state: MainActivityUiState) {
        when (state) {
            is MainActivityUiState.Init -> { /* Do Nothing */
            }
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