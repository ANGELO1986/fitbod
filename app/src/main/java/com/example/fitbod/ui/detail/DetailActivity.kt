package com.example.fitbod.ui.detail

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.ui.Tooltip
import com.example.fitbod.databinding.ActivityDetailBinding
import com.example.fitbod.model.Exercise
import com.robinhood.spark.SparkView
import java.util.*


class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_EXERCISE = "DetailActivity:exercise"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exercise = intent.getParcelableExtra<Exercise>(EXTRA_EXERCISE)
        if(exercise != null){
            title = exercise.name
            binding.exerciseRowView.setExercise(exercise)
            funGenerateChart(binding, exercise)

        }
    }

    private fun funGenerateChart(binding: ActivityDetailBinding, exercise: Exercise) {
       val pie : Cartesian? = AnyChart.bar()
            val data : ArrayList<DataEntry> = arrayListOf()
            exercise.logs.sort()
            for(log in exercise.logs){
                data.add(ValueDataEntry(log.date, log.maxRep))
            }
            if (pie != null) {
                pie.data(data)
            }
            var tooltip : Tooltip? = pie?.tooltip()
            var backgrounGraph : String
            if(tooltip != null){
                 backgrounGraph  = tooltip.background().toString()
            }


            binding.graph.setChart(pie)
    }

}