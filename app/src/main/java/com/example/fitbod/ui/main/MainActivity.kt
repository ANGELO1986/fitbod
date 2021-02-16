package com.example.fitbod.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitbod.MainViewModel
import com.example.fitbod.databinding.ActivityMainBinding
import com.example.fitbod.model.Exercise
import com.example.fitbod.ui.ExerciseAdapter
import com.example.fitbod.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ExerciseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.progressVisibility.observe(this, Observer {
            binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })

        //binding.recycler.adapter
        adapter = ExerciseAdapter(
            listOf()
        ){
            navigateTo(it)
        }
        binding.recycler.adapter = adapter

        viewModel.listOfExercises.observe(this, Observer {
            adapter.update(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.loadExercises(this)






//        binding.recycler.adapter = ExerciseAdapter(
//            viewModel.loadExercises(application.applicationContext)
//        ) {
//            navigateTo(it)
//        }
    }

    private fun navigateTo(it: Exercise) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_EXERCISE,it)
        startActivity(intent)
    }


}