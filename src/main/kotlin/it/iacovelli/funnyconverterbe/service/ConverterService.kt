package it.iacovelli.funnyconverterbe.service

/**
 * Service interface exposing methods used for unit conversion
 * @author Nicola Iacovelli
 */
interface ConverterService {

    /**
     * Method which take the source unit and value and convert it to destination unit
     * @param from source unit
     * @param to destination unit
     * @param value to convert
     * @return the converted value
     */
    fun convert(from: String, to: String, value: Double): Double

}