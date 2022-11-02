package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.mikrusek.generated.api.model.ApiResponse
import com.bladyzamosc.mikrusek.generated.api.model.TimeSeries
import org.springframework.stereotype.Service

/**
 * User: bladyzamosc
 * Date: 14.10.2022
 */
@Service
class TimeSeriesAddService(
    private val validator: TimeSeriesInputValidator,
    private val mikrusekCreator: MikrusekMessageCreator,
    private val converter: TimeSeriesConverter,
    private val messageService: TimeSeriesMikrusekMessageService
) {

    fun addTimeSeries(timeSeries: TimeSeries): ApiResponse {
        validator.validate(timeSeries);
        handle(timeSeries)
        return ApiResponse(code = 1, message = "Successfully added", type = "TimeSeries")
    }

    private fun handle(timeSeries: TimeSeries) {
        val timeSeriesSection = converter.convert(timeSeries)
        val mikrusekMessage = mikrusekCreator.createMessageWithTimeSeriesSection(timeSeriesSection)
        messageService.send(mikrusekMessage)
    }
}