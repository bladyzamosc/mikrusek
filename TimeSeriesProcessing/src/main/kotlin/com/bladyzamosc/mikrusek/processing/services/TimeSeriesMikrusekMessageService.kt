package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.protocol.MikrusekMessage
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.lognet.springboot.grpc.GRpcService

/**
 * User: Z6EKI
 * Date: 28.10.2022
 */
@GRpcService
class TimeSeriesMikrusekMessageService(private val producer: KafkaProducer) {

    fun send(request: MikrusekMessage) {
        val gson = Gson()
        producer.sendMessage("add-time-series", gson.toJson(request))
    }
}