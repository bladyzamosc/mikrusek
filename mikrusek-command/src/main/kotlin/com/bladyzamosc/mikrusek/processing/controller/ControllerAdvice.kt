package com.bladyzamosc.mikrusek.processing.controller

import com.bladyzamosc.mikrusek.generated.api.model.ApiResponse
import com.bladyzamosc.mikrusek.processing.exceptions.TimeSeriesProcessingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * User: Bladyzamosc
 * Date: 18.10.2022
 */
@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler
    public fun handle(ex: TimeSeriesProcessingException): ResponseEntity<ApiResponse> {
        val response = ApiResponse(code = 405, message = ex.message, type = "error")
        return ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED)
    }
}