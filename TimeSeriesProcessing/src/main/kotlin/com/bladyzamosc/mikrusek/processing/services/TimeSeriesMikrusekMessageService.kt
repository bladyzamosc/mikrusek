package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.protocol.MikrusekMessage
import org.lognet.springboot.grpc.GRpcService

private const val ADD_TIME_SERIES_TOPIC_NAME = "add-time-series"

/**
 * User: Z6EKI
 * Date: 28.10.2022
 */
@GRpcService
class TimeSeriesMikrusekMessageService(private val producer: KafkaProducer) {

    fun send(request: MikrusekMessage) {
        producer.sendMessage(ADD_TIME_SERIES_TOPIC_NAME, request)
    }
}