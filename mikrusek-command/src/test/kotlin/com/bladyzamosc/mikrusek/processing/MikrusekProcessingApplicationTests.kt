package com.bladyzamosc.mikrusek.processing

import com.bladyzamosc.mikrusek.generated.api.model.ApiResponse
import com.bladyzamosc.mikrusek.generated.api.model.Node
import com.bladyzamosc.mikrusek.generated.api.model.TimeSeries
import com.bladyzamosc.mikrusek.generated.api.model.Value
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity


@SpringBootTest(
    classes = arrayOf(MikrusekProcessingApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class MikrusekProcessingApplicationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun contextLoads() {
    }

    @Test
    fun validatorMandatoryFieldsTimeSeries() {
        var timeSeries = TimeSeries()
        var result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("NodeId is required", result.body?.message)
        assertEquals(405, result.statusCodeValue)
        timeSeries = TimeSeries(nodeId = 1)
        result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("Timestamp is required", result.body?.message)
        assertEquals(405, result.statusCodeValue)
        timeSeries = TimeSeries(nodeId = 1, timestamp = 1234455)
        result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("Values must be set", result.body?.message)
        assertEquals(405, result.statusCodeValue)
        var array = arrayOf<Value>()
        timeSeries = TimeSeries(nodeId = 1, timestamp = 1234455, values = array)
        result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("Non-empty values is required", result.body?.message)
        assertEquals(405, result.statusCodeValue)
    }

    @Test
    fun validatorMandatoryFieldsNodePost() {
        var node = Node()
        var result = restTemplate.postForEntity("/nodes", node, ApiResponse::class.java)
        assertEquals("Name is required", result.body?.message)
        assertEquals(405, result.statusCodeValue)
    }

    @Test
    fun validatorMandatoryFieldsNodePut() {
        var node = Node()
        var entity = HttpEntity<Node>(node)
        var response: ResponseEntity<ApiResponse> =
            restTemplate.exchange("/nodes", HttpMethod.PUT, entity, ApiResponse::class.java)
        assertEquals("Id is required", response.body?.message)
        assertEquals(405, response.statusCodeValue)
        node = Node(id = 1234)
        entity = HttpEntity<Node>(node)
        response = restTemplate.exchange("/nodes", HttpMethod.PUT, entity, ApiResponse::class.java)
        assertEquals("Name is required", response.body?.message)
        assertEquals(405, response.statusCodeValue)
    }

}
