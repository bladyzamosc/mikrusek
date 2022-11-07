package com.bladyzamosc.mikrusek.processing.controller

import com.bladyzamosc.mikrusek.generated.api.model.ApiResponse
import com.bladyzamosc.mikrusek.generated.api.model.Node
import com.bladyzamosc.mikrusek.processing.services.NodeService
import org.springframework.web.bind.annotation.*

/**
 * User: Z6EKI
 * Date: 07.11.2022
 */
@RestController()
@RequestMapping("nodes")
class NodeApiController (val nodeService: NodeService){

    @PostMapping
    fun addNode(@RequestBody node : Node) : ApiResponse {
        return nodeService.addNode(node)
    }

    @PutMapping
    fun updateNode(@RequestBody node : Node) : ApiResponse {
        return nodeService.updateNode(node)
    }

}