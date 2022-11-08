package com.bladyzamosc.engine.mirusekengine.consumer

import com.bladyzamosc.protocol.MikrusekMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload

/**
 * User: Z6EKI
 * Date: 08.11.2022
 */
class Listener {

    @KafkaListener(id = "listen-add-time-series", topics = ["add-time-series"])
    fun listenAddTimeSeries(@Payload message : MikrusekMessage) {

    }

    @KafkaListener(id = "listen-node", topics = ["node"])
    fun listenNode(@Payload message : MikrusekMessage) {

    }

}