package com.example.fitbod

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbod.model.Exercise
import com.example.fitbod.model.ExerciseLog
import com.example.fitbod.model.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> get() = _progressVisibility

    private val _listOfExercises =  MutableLiveData<List<Exercise>>()
    val listOfExercises : MutableLiveData<List<Exercise>> get() = _listOfExercises

    fun loadExercises(ctx : Context){
        viewModelScope.launch {
            _progressVisibility.value = true
            withContext(Dispatchers.IO){
                val listOfLogs : List<String> = Utils.getListOfLogs("workoutData.txt",ctx)
                val listExerciseLog: MutableList<ExerciseLog> = createListOfExercisesLog(listOfLogs)
                _listOfExercises.postValue(createListOfExercise(listExerciseLog))
            }
            _progressVisibility.value = false
        }
    }

    private fun calculateMaxRep(log: ExerciseLog): Double{
        return log.weight*(36/(37-log.reps))
    }

    private fun createListOfExercise(listOfLogs : MutableList<ExerciseLog>): MutableList<Exercise>{
        val listOfExercises: ArrayList<Exercise> = arrayListOf()
        var logs: MutableList<ExerciseLog>
        var auxExercise : Exercise
        var position : Int
        var positionExercise : Int
        for(exerciseLog in listOfLogs){
            auxExercise = Exercise(exerciseLog.name)
            if(listOfExercises.contains(auxExercise)){
                position = listOfExercises.indexOf(auxExercise)
                listOfExercises[position].logs.add(exerciseLog)
                if(listOfExercises[position].max < exerciseLog.maxRep){
                    listOfExercises[position].max = exerciseLog.maxRep
                }
            }else{
                if(listOfExercises.contains(exerciseLog.name)){
                    positionExercise = listOfExercises.indexOf(exerciseLog.name)
                    listOfExercises[positionExercise].logs.add(exerciseLog)
                }else{
                    logs = mutableListOf()
                    logs.add(exerciseLog)
                    auxExercise = Exercise(logs,exerciseLog.name,exerciseLog.maxRep)
                    listOfExercises.add(auxExercise)
                }
            }
        }
        return listOfExercises
    }

    private fun createListOfExercisesLog(listOfLogs: List<String>) : MutableList<ExerciseLog>{
        var arraySplits: List<String>
        var currentLog : ExerciseLog
        val listExerciseLog: MutableList<ExerciseLog> = mutableListOf()
        var previousLog: ExerciseLog
        var position: Int
        for(string in listOfLogs){
            arraySplits = string.split(",")
            currentLog= ExerciseLog(arraySplits[0],arraySplits[1],arraySplits[2].toInt(),arraySplits[3].toInt(),arraySplits[4].toDouble(),0.0)
            currentLog.maxRep = calculateMaxRep(currentLog)
            if(listExerciseLog.contains(currentLog)){
                position = listExerciseLog.indexOf(currentLog)
                previousLog = listExerciseLog[position]
                if(previousLog == currentLog){
                    listExerciseLog[position] = currentLog
                }else{
                    listExerciseLog.add(currentLog)
                }
            }else{
                listExerciseLog.add(currentLog)
            }
        }
        return listExerciseLog
    }
}

