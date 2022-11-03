package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.mikrusek.generated.api.model.TimeSeries
import com.bladyzamosc.mikrusek.generated.api.model.Value
import com.bladyzamosc.protocol.TimeSeriesSection
import com.bladyzamosc.protocol.TimeSeriesValue
import org.springframework.stereotype.Service

@Service
class TimeSeriesConverter {
    fun convert(timeSeries: TimeSeries): TimeSeriesSection {
        return TimeSeriesSection.newBuilder()
            .setNodeId(timeSeries.nodeId ?: 0)
            .setTimestamp(timeSeries.timestamp ?: 0)
            .setTimestampFrom(timeSeries.timestampFrom ?: 0)
            .addAllValues(convertValues(timeSeries.values))
            .build()
    }

    private fun convertValues(values: Array<Value>?): List<TimeSeriesValue>? {
        return values?.map { a ->
            TimeSeriesValue.newBuilder()
                .setKey(a.key)
                .setValue(a.value ?: Long.MIN_VALUE)
                .build()
        }
    }
}
