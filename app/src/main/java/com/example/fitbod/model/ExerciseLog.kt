package com.example.fitbod.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Parcelize
data class ExerciseLog (val date: String, val name: String, val set: Int, val reps: Int, val weight: Double, var maxRep: Double) : Comparable<ExerciseLog>,
    Parcelable {
    override operator fun compareTo(other: ExerciseLog): Int{
        return if(this.date.equals(other.date) && this.name.equals(other.name)){
            this.maxRep.compareTo(other.maxRep)
        }else{
            if(this.name.equals(other.name)){
                val formatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
                LocalDate.parse(this.date, formatter).compareTo(LocalDate.parse(other.date, formatter))
            }else{
                this.name.compareTo(other.name)
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

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + set
        result = 31 * result + reps
        result = 31 * result + weight.hashCode()
        result = 31 * result + maxRep.hashCode()
        return result
    }
}
