package com.bladyzamosc.engine.mirusekengine.services

import com.bladyzamosc.engine.mirusekengine.model.NodeItem
import com.bladyzamosc.engine.mirusekengine.repos.NodeItemRepository
import com.bladyzamosc.engine.mirusekengine.validators.NodeValidator
import com.bladyzamosc.protocol.MikrusekMessage
import org.springframework.stereotype.Service
import java.util.*

/**
 * User: Z6EKI
 * Date: 14.11.2022
 */
@Service
class NodeServiceImpl(
    val validator: NodeValidator,
    val repository: NodeItemRepository
) {
    fun handleAddNode(message: MikrusekMessage?) {
        validator.validate(message)
        val nodeSection = message?.payload?.nodeSection
        var item = NodeItem(
            UUID.randomUUID().toString(),
            nodeSection?.name ?: "", nodeSection?.number ?: ""
        )
        repository.save(item)
    }
}