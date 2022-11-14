package com.bladyzamosc.engine.mirusekengine.repos

import com.bladyzamosc.engine.mirusekengine.model.NodeItem
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * User: Z6EKI
 * Date: 14.11.2022
 */
interface NodeItemRepository : MongoRepository<NodeItem, String> {
}