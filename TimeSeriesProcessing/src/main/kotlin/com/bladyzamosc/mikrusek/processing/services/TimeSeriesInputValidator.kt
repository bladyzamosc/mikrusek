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
        if (timeSeries.values == null || timeSeries.values.isEmpty()) {
            throw TimeSeriesProcessingException("Non-empty values is required")
        }
    }

    private fun validateMandatoryFields(timeSeries: TimeSeries) {
        if (timeSeries.nodeId == null) {
            throw TimeSeriesProcessingException("NodeId is required")
        }

        if (timeSeries.timestamp == null) {
            throw TimeSeriesProcessingException("Timestamp is required")
        }
    }

}
