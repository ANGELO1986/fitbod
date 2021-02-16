package com.example.fitbod

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.fitbod.model.Exercise

class ExerciseRowView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val nameExercise : TextView
    private val maxRepValue: TextView

    init{
        val view = LayoutInflater.from(context).inflate(R.layout.view_exercise_row_view, this, true)
        nameExercise = view.findViewById(R.id.nameExercise)
        maxRepValue = view.findViewById(R.id.maxRepValue)
    }

    fun setExercise(exercise: Exercise){
        nameExercise.text = exercise.name
        maxRepValue.text = exercise.max.toString()
    }
}