package com.example.cakearea

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cakearea.databinding.ActivityMainBinding
import com.example.cakearea.ui.CakeAdapter
import com.example.cakearea.viewmodel.CakeViewModel
import kotlin.collections.get

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : CakeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Function Calls
        setupRecyclerView()
        setupViewModel()
        observeData()
        viewModel.fetchFlowers()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        binding.rvCakes.layoutManager = LinearLayoutManager(this)
    }

    // observe data
    private fun observeData() {
        viewModel.flowers.observe(this) { cakes ->
            Log.i("MainActivity",cakes[0].title);
            binding.rvCakes.adapter = CakeAdapter(cakes)
        }

        viewModel.loading.observe(this) { isLoading ->
            // Show Spinner
        }

        viewModel.error.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }
    }

    // setup VM
    private fun setupViewModel() {
        // TODO Hilt DI
        viewModel = ViewModelProvider(this)[CakeViewModel::class.java]
    }
}