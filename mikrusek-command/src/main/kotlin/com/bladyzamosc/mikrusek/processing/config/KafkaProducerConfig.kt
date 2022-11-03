package com.bladyzamosc.mikrusek.processing.config

import com.bladyzamosc.protocol.MikrusekMessage
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
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
open class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    @Value("\${mikrusek.schema.registry.url}")
    private val schemaRegistry: String? = null

    @Bean
    open fun producerConfigs(): Map<String, Any>? {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers!!
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaProtobufSerializer::class.java
        props[KafkaProtobufSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG] = schemaRegistry!!
        return props
    }

    @Bean
    open fun producerFactory(): ProducerFactory<String, MikrusekMessage> {
        return DefaultKafkaProducerFactory(producerConfigs()!!)
    }

    @Bean
    open fun kafkaTemplate(): KafkaTemplate<String, MikrusekMessage> {
        return KafkaTemplate(producerFactory())
    }
}