package com.bladyzamosc.engine.mirusekengine.config

import com.bladyzamosc.engine.mirusekengine.consumer.Listener
import com.bladyzamosc.protocol.MikrusekMessage
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import java.util.HashMap

/**
 * User: Z6EKI
 * Date: 08.11.2022
 */
@Configuration
@EnableKafka
class EngineConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    @Value("\${mikrusek.schema.registry.url}")
    private val schemaRegistry: String? = null

    private fun consumerProps(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers!!)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group")
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaProtobufDeserializer::class.java)
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
        props.put(KafkaProtobufSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistry!!)
        return props
    }

    @Bean
    fun consumerFactory() = DefaultKafkaConsumerFactory<String, MikrusekMessage>(consumerProps())

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String?, MikrusekMessage?>): ConcurrentKafkaListenerContainerFactory<String, MikrusekMessage>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, MikrusekMessage>()
        factory.consumerFactory = consumerFactory
        return factory
    }

    @Bean
    fun listener() = Listener()
}