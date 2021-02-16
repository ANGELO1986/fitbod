package com.example.fitbod.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
        if(name == other.name){
            return true
        }else{
            return false
        }

    }
}

