package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.protocol.MikrusekMessage
import com.bladyzamosc.protocol.MikrusekMessageHeader
import com.bladyzamosc.protocol.MikrusekMessagePayload
import com.bladyzamosc.protocol.TimeSeriesSection
import org.springframework.stereotype.Service
import java.util.*

private const val TIME_SERIES_PROCESSING = "TimeSeriesProcessing"

private const val s = TIME_SERIES_PROCESSING

/**
 * User: Z6EKI
 * Date: 27.10.2022
 */
@Service
class MikrusekMessageCreator {
    fun createMessageWithTimeSeriesSection(timeSeriesSection: TimeSeriesSection): MikrusekMessage {
        return MikrusekMessage.newBuilder()
            .setHeader(createHeader())
            .setPayload(createPayloadWithTimeSeriesSection(timeSeriesSection))
            .build();
    }

    private fun createPayloadWithTimeSeriesSection(timeSeriesSection: TimeSeriesSection): MikrusekMessagePayload {
        return MikrusekMessagePayload.newBuilder()
            .setTimeSeriesSection(timeSeriesSection)
            .build()
    }

    private fun createHeader(): MikrusekMessageHeader {
        val header = MikrusekMessageHeader.newBuilder()
            .setMessageId(UUID.randomUUID().toString())
            .setMessageTimestamp(System.currentTimeMillis())
            .setSource(TIME_SERIES_PROCESSING)
            .setVersion("1.0")
            .build();
        return header
    }
}