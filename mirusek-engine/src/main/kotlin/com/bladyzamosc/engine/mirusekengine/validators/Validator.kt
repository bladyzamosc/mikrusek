package com.bladyzamosc.engine.mirusekengine.validators

import com.bladyzamosc.protocol.MikrusekMessage

/**
 * User: Z6EKI
 * Date: 14.11.2022
 */
interface Validator {
    fun validate(message: MikrusekMessage?)
}