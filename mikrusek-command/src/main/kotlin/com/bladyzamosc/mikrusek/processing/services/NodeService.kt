package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.mikrusek.generated.api.model.ApiResponse
import com.bladyzamosc.mikrusek.generated.api.model.Node
import org.springframework.stereotype.Service

private const val NODE_TOPIC_NAME = "node"

/**
 * User: Z6EKI
 * Date: 07.11.2022
 */
@Service
class NodeService (private val validator: NodeInputValidator,
                    private val converter: NodeConverter,
                   private val producer: KafkaProducer,
                   private val mikrusekCreator: MikrusekMessageCreator,
                   ){
    fun addNode(node: Node): ApiResponse {
        validator.validateAdd(node)
        handle(node)
        return ApiResponse(code = 1, message = "Successfully added", type = "Node")
    }

    fun updateNode(node: Node): ApiResponse {
        validator.validateUpdate(node)
        handle(node)
        return ApiResponse(code = 1, message = "Successfully updated", type = "Node")
    }

    private fun handle(node: Node) {
        val nodeSection = converter.convert(node)
        val mikrusekMessage = mikrusekCreator.createMessageWithNodeSection(nodeSection)
        producer.sendMessage(NODE_TOPIC_NAME, mikrusekMessage)
    }
}