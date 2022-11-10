package com.bladyzamosc.engine.mirusekengine.consumer

import com.bladyzamosc.protocol.MikrusekMessage
import com.google.protobuf.DynamicMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload

/**
 * User: Z6EKI
 * Date: 08.11.2022
 */
class Listener {

    @KafkaListener(id = "listen-add-time-series", topics = ["add-time-series"])
    fun listenAddTimeSeries(@Payload message : DynamicMessage) {
        val parsedMessage = convertMessage(message)
        println(parsedMessage)
    }

    private fun convertMessage(message: DynamicMessage) =
        MikrusekMessage.parseFrom(message.toByteArray())

    @KafkaListener(id = "listen-node", topics = ["node"])
    fun listenNode(@Payload message : DynamicMessage) {
        val parsedMessage = convertMessage(message)
        println(parsedMessage)
    }

}