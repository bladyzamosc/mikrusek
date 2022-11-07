package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.mikrusek.generated.api.model.Node
import com.bladyzamosc.mikrusek.processing.exceptions.ProcessingException
import org.springframework.stereotype.Service

/**
 * User: Z6EKI
 * Date: 07.11.2022
 */
@Service
class NodeInputValidator {
    fun validateAdd(node: Node) {
        node.name ?: throw ProcessingException("Name is required")
    }

    fun validateUpdate(node: Node) {
        node.id ?: throw ProcessingException("Id is required")
        node.name ?: throw ProcessingException("Name is required")
    }
}