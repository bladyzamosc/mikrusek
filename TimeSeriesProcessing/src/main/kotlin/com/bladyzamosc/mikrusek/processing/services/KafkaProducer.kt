package com.bladyzamosc.mikrusek.processing.services

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
    private val kafkaTemplate: KafkaTemplate<Int, String>? = null

    fun sendMessage(topic: String?, message: String?) {
        val future = kafkaTemplate!!.send(
            topic!!, message
        )
        future.addCallback(object : ListenableFutureCallback<SendResult<Int?, String?>?> {
            override fun onSuccess(result: SendResult<Int?, String?>?) {
                LOGGER.info("sent message='{}' with offset={}", message, result!!.recordMetadata.offset())
            }

            override fun onFailure(ex: Throwable) {
                LOGGER.error("unable to send message='{}'", message, ex)
            }
        })
    }
}