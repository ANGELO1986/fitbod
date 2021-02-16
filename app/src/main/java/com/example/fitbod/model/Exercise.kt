package com.example.fitbod.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise (val logs: MutableList<ExerciseLog>, val name: String, var max: Double) : Parcelable {

    constructor(name: String) : this(
            mutableListOf<ExerciseLog>(),
            name,
            0.0
    )

    override fun equals(other: Any?): Boolean {
        if (this === other){
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Exercise
        return name == other.name

    }

    override fun hashCode(): Int {
        var result = logs.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + max.hashCode()
        return result
    }
}

