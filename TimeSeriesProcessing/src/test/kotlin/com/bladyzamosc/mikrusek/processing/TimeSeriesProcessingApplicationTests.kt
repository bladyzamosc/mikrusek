package com.bladyzamosc.mikrusek.processing

import com.bladyzamosc.mikrusek.generated.api.model.ApiResponse
import com.bladyzamosc.mikrusek.generated.api.model.TimeSeries
import com.bladyzamosc.mikrusek.generated.api.model.Value
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate


@SpringBootTest(
    classes = arrayOf(TimeSeriesProcessingApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class TimeSeriesProcessingApplicationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun contextLoads() {
    }

    @Test
    fun validatorMandatoryFields() {
        var timeSeries = TimeSeries()
        var result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("NodeId is required", result.body.message)
        assertEquals(405, result.statusCodeValue)
        timeSeries = TimeSeries(nodeId = 1)
        result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("Timestamp is required", result.body.message)
        assertEquals(405, result.statusCodeValue)
        timeSeries = TimeSeries(nodeId = 1, timestamp = 1234455)
        result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("Values must be set", result.body.message)
        assertEquals(405, result.statusCodeValue)
        var array = arrayOf<Value>()
        timeSeries = TimeSeries(nodeId = 1, timestamp = 1234455, values= array)
        result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("Non-empty values is required", result.body.message)
        assertEquals(405, result.statusCodeValue)
        val value = Value(value = 1, key = "a")
        array = arrayOf<Value>(value)
        timeSeries = TimeSeries(nodeId = 1, timestamp = 1234455, values= array)
        result = restTemplate.postForEntity("/timeseries", timeSeries, ApiResponse::class.java)
        assertEquals("Successfully added", result.body.message)
        assertEquals(200, result.statusCodeValue)

    }

}
