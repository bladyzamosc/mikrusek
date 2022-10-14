package com.bladyzamosc.mikrusek.processing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TimeSeriesProcessingApplication

fun main(args: Array<String>) {
    runApplication<TimeSeriesProcessingApplication>(*args)
}
