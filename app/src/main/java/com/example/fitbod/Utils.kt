package com.example.fitbod

import android.content.Context
import java.io.BufferedReader
import java.io.IOException

object Utils{
    fun getListOfLogs(fileName :String, ctx: Context): List<String>{
        var listOfLogs: List<String> = listOf<String>()
        try {
            listOfLogs = readFileAsLinesUsingReadLines("workoutData.txt",ctx)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listOfLogs
    }

    fun readFileAsLinesUsingReadLines(fileName: String, ctx: Context): List<String>{
        return ctx
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readLines)
    }
}



