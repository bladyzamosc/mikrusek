package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.protocol.*
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
            .setPayload(
                MikrusekMessagePayload.newBuilder()
                    .setTimeSeriesSection(timeSeriesSection)
                    .build()
            )
            .build();
    }

    fun createMessageWithNodeSection(nodeSection: NodeSection): MikrusekMessage {
        return MikrusekMessage.newBuilder()
            .setHeader(createHeader())
            .setPayload(
                MikrusekMessagePayload.newBuilder()
                    .setNodeSection(nodeSection)
                    .build()
            )
            .build();
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