package it.iacovelli.funnyconverterbe.service.impl

import it.iacovelli.funnyconverterbe.config.ConversionConfig
import it.iacovelli.funnyconverterbe.service.ConverterService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Implementation of the ConverterService interface
 * @author Nicola Iacovelli
 * @version 1.0
 */
@Service
class ConverterServiceImpl(val conversionConfig: ConversionConfig) : ConverterService {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Implementation method used to convert value with the following formulae (value * fromFactor) / toFactor
     * @param from source unit
     * @param to destination unit
     * @param value to convert
     * @return the converted value
     */
    override fun convert(from: String, to: String, value: Double): Double {
        val fromFactor = conversionConfig.conversionFactorsMap[from]!!
        val toFactor = conversionConfig.conversionFactorsMap[to]!!
        val resultValue = (value * fromFactor) / toFactor
        log.debug("{} {} is equal to {} {}", value, from, resultValue, to)
        return resultValue
    }
}