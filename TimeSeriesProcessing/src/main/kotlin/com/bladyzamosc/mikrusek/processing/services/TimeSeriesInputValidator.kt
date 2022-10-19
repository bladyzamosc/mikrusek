package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.mikrusek.generated.api.model.TimeSeries
import com.bladyzamosc.mikrusek.processing.exceptions.TimeSeriesProcessingException
import org.springframework.stereotype.Service

@Service
class TimeSeriesInputValidator {
    fun validate(timeSeries: TimeSeries) {
        validateMandatoryFields(timeSeries);
        validateValues(timeSeries)
    }

    private fun validateValues(timeSeries: TimeSeries) {
        timeSeries.values ?: throw TimeSeriesProcessingException("Values must be set")
        if (timeSeries.values.isEmpty()) {
            throw TimeSeriesProcessingException("Non-empty values is required")
        }
    }

    private fun validateMandatoryFields(timeSeries: TimeSeries) {
        timeSeries.nodeId ?: throw TimeSeriesProcessingException("NodeId is required")
        timeSeries.timestamp ?: throw TimeSeriesProcessingException("Timestamp is required")
    }

}
