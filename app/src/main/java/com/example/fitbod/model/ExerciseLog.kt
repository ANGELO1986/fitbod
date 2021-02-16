package com.example.fitbod.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Parcelize
data class ExerciseLog (val date: String, val name: String, val set: Int, val reps: Int, val weight: Double, var maxRep: Double) : Comparable<ExerciseLog>,
    Parcelable {
    override operator fun compareTo(other: ExerciseLog): Int{
        if(this.date.equals(other.date) && this.name.equals(other.name)){
            return this.maxRep.compareTo(other.maxRep)
        }else{
            if(this.name.equals(other.name)){
                var formatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
                return LocalDate.parse(this.date, formatter).compareTo(LocalDate.parse(other.date, formatter))
            }else{
                return this.name.compareTo(other.name)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ExerciseLog
        if (date == other.date && name == other.name) return true
        else return false

    }
}
