package com.bladyzamosc.mikrusek.processing.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback
import java.util.*

/**
 * User: Z6EKI
 * Date: 28.10.2022
 */
@Service
class KafkaProducer {
    private val LOGGER: Logger = LoggerFactory.getLogger("KafkaProducer")

    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>? = null

    fun sendMessage(topic: String?, message: String?) {
        val key = UUID.randomUUID().toString()
        val future = kafkaTemplate!!.send(topic!!,key,  message)
        future.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onSuccess(result: SendResult<String?, String?>?) {
                LOGGER.info("Message sent with offset={}, key={}, message='{}'", result!!.recordMetadata.offset(), key, message)
            }

            override fun onFailure(ex: Throwable) {
                LOGGER.error("unable to send message='{}'", message, ex)
            }
        })
    }
}