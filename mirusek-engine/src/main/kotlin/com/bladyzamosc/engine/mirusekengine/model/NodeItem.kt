package com.bladyzamosc.engine.mirusekengine.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * User: Z6EKI
 * Date: 14.11.2022
 */
@Document("nodeitems")
class NodeItem(id: String, name: String, number: String) {
    @Id
    private val id: String
    private val name: String
    private val number: String

    init {
        this.id = id
        this.name = name
        this.number = number
    }
}