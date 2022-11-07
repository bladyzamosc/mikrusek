package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.mikrusek.generated.api.model.TimeSeries
import com.bladyzamosc.mikrusek.processing.exceptions.ProcessingException
import org.springframework.stereotype.Service

@Service
class TimeSeriesInputValidator {
    fun validate(timeSeries: TimeSeries) {
        validateMandatoryFields(timeSeries);
        validateValues(timeSeries)
    }

    private fun validateValues(timeSeries: TimeSeries) {
        timeSeries.values ?: throw ProcessingException("Values must be set")
        if (timeSeries.values.isEmpty()) {
            throw ProcessingException("Non-empty values is required")
        }
    }

    private fun validateMandatoryFields(timeSeries: TimeSeries) {
        timeSeries.nodeId ?: throw ProcessingException("NodeId is required")
        timeSeries.timestamp ?: throw ProcessingException("Timestamp is required")
    }

}
