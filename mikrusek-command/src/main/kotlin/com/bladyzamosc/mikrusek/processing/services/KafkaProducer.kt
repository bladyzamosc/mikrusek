package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.protocol.MikrusekMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback

/**
 * User: Z6EKI
 * Date: 28.10.2022
 */
@Service
class KafkaProducer {
    private val LOGGER: Logger = LoggerFactory.getLogger("KafkaProducer")

    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, MikrusekMessage>? = null

    fun sendMessage(topic: String?, request: MikrusekMessage) {
        val future = kafkaTemplate!!.send(topic ?: "no-name-topic", request.header.messageId , request)
        future.addCallback(object : ListenableFutureCallback<SendResult<String?, MikrusekMessage?>?> {
            override fun onSuccess(result: SendResult<String?, MikrusekMessage?>?) {
                LOGGER.info("Message sent with offset={}, key={}, message='{}'",
                    result!!.recordMetadata.offset(),request.header.messageId, request)
            }

            override fun onFailure(ex: Throwable) {
                LOGGER.error("unable to send message='{}'", request, ex)
            }
        })
    }
}