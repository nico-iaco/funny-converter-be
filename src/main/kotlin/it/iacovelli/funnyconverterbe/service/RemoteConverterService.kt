package it.iacovelli.funnyconverterbe.service

import it.iacovelli.funnyconverterbe.exception.UnitConversionException

/**
 * Service interface exposing method used to convert unknown unit calling a remote api
 * @author Nicola Iacovelli
 * @version 1.0
 */
interface RemoteConverterService {

    /**
     * Method which calls an external api to try to convert the value to destination unit
     * @param from source unit
     * @param value to convert
     * @return converted value
     * @throws UnitConversionException if the conversion was not successful
     */
    @Throws(UnitConversionException::class)
    fun convert(from: String, to: String, value: Double): Double

}