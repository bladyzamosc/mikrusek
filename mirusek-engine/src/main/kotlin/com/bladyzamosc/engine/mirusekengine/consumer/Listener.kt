package com.bladyzamosc.engine.mirusekengine.consumer

import com.bladyzamosc.engine.mirusekengine.services.NodeServiceImpl
import com.bladyzamosc.protocol.MikrusekMessage
import com.google.protobuf.DynamicMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

/**
 * User: Z6EKI
 * Date: 08.11.2022
 */
@Service
class Listener (val nodeService: NodeServiceImpl){

    @KafkaListener(id = "listen-add-time-series", topics = ["add-time-series"])
    fun listenAddTimeSeries(@Payload message : DynamicMessage) {
        val parsedMessage = convertMessage(message)

    }

    private fun convertMessage(message: DynamicMessage) =
        MikrusekMessage.parseFrom(message.toByteArray())

    @KafkaListener(id = "listen-node", topics = ["node"])
    fun listenNode(@Payload message : DynamicMessage) {
        val parsedMessage = convertMessage(message)
        nodeService.handleAddNode(parsedMessage)
    }

}