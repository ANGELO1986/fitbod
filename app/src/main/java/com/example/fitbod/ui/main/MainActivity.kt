package com.example.fitbod.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.fitbod.MainViewModel
import com.example.fitbod.R
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
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
        //binding.toolbar.setBackgroundColor(resources.getColor(R.color.orangeFitbod))
        binding.toolbar.setTitle(R.string.app_name)
        setSupportActionBar(binding.toolbar)

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(getString(R.string.fitbod_orange))

        adapter = ExerciseAdapter(
            listOf()
        ){
            navigateTo(it)
        }

        binding.recycler.adapter = adapter
        binding.recycler.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

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
        intent.putExtra(DetailActivity.EXTRA_EXERCISE, it)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}