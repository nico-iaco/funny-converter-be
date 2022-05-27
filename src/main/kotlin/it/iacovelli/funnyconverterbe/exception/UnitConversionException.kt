package it.iacovelli.funnyconverterbe.exception

/**
 * Exception thrown if the unit conversion was not successfully completed
 * @param msg with the specific error
 * @author Nicola Iacovelli
 * @version 1.0
 */
class UnitConversionException(msg: String): RuntimeException(msg)