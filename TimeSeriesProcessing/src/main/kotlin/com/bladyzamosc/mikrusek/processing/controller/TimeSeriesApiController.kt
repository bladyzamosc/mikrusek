package com.bladyzamosc.mikrusek.processing.controller

import com.bladyzamosc.mikrusek.generated.api.model.TimeSeries
import com.bladyzamosc.mikrusek.processing.services.TimeSeriesAddService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * User: bladyzamosc
 * Date: 14.10.2022
 */
@RestController("timeseries")
class TimeSeriesApiController  (val serviceAdd: TimeSeriesAddService){

    @PostMapping
    fun addTimeSeries(@RequestBody timeSeries : TimeSeries)  {
        serviceAdd.addTimeSeries(timeSeries)
    }
}