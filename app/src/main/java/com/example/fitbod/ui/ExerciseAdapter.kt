package com.example.fitbod.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbod.ExerciseRowView
import com.example.fitbod.databinding.ExerciseRowBinding
import com.example.fitbod.databinding.ViewExerciseRowViewBinding
import com.example.fitbod.model.Exercise
import com.example.fitbod.model.ExerciseLog

class ExerciseAdapter(
    private var exercises: List<Exercise>,
    private val exerciseClickListener: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        //val binding = ExerciseRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        val binding = ViewExerciseRowViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises.get(position)
        holder.bind(exercise)
        holder.itemView.setOnClickListener { exerciseClickListener(exercise)}
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    fun update(newExercises : List<Exercise>){
        exercises = newExercises
        notifyDataSetChanged()
    }

    //class ExerciseViewHolder(private val binding: ExerciseRowBinding): RecyclerView.ViewHolder(binding.root){
    class ExerciseViewHolder(private val binding: ViewExerciseRowViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(exercise: Exercise){
            binding.nameExercise.text = exercise.name
            binding.maxRepValue.text = exercise.max.toString()
        }
    }
}