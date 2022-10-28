package com.bladyzamosc.mikrusek.processing.config

import com.fasterxml.jackson.databind.ser.std.NumberSerializers
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

/**
 * User: Z6EKI
 * Date: 28.10.2022
 */
@Configuration
class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    @Bean
    fun producerConfigs(): Map<String, Any>? {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers!!
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = NumberSerializers.IntegerSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.MAX_BLOCK_MS_CONFIG] = 5000
        return props
    }

    @Bean
    fun producerFactory(): ProducerFactory<Int, String> {
        return DefaultKafkaProducerFactory(producerConfigs()!!)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<Int, String> {
        return KafkaTemplate(producerFactory())
    }
}