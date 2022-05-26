package it.iacovelli.funnyconverterbe.model

/**
 * Request class used in convert api
 * @param from the unit from which convert
 * @param to the unit to which convert
 * @param value to convert
 * @author Nicola Iacovelli
 * @version 1.0
 */
class ConvertRequest(var from: String, var to: String, var value: Double)