package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.mikrusek.generated.api.model.Node
import com.bladyzamosc.protocol.NodeSection
import org.springframework.stereotype.Service

/**
 * User: Z6EKI
 * Date: 07.11.2022
 */
@Service
class NodeConverter {
    fun convert(node: Node): NodeSection {
        return NodeSection.newBuilder()
            .setId(node.id ?: 0)
            .setName(node.name ?: null)
            .setNumber(node.number ?: null)
            .build()
    }
}