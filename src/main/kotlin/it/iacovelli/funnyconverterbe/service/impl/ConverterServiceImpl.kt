package it.iacovelli.funnyconverterbe.service.impl

import it.iacovelli.funnyconverterbe.config.ConversionConfig
import it.iacovelli.funnyconverterbe.service.ConverterService
import it.iacovelli.funnyconverterbe.service.RemoteConverterService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Implementation of the ConverterService interface
 * @author Nicola Iacovelli
 * @version 1.0
 */
@Service
class ConverterServiceImpl(val conversionConfig: ConversionConfig, val remoteConverterService: RemoteConverterService) : ConverterService {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    private val millimeterUnit = "millimeter"

    /**
     * Implementation method used to convert value with the following formulae (value * fromFactor) / toFactor
     * @param from source unit
     * @param to destination unit
     * @param value to convert
     * @return the converted value
     */
    override fun convert(from: String, to: String, value: Double): Double {
        var fromFactor = conversionConfig.conversionFactorsMap[from]
        var toFactor = conversionConfig.conversionFactorsMap[to]
        var v = value
        if (fromFactor == null && toFactor == null) {
            v = remoteConverterService.convert(from, to, value)
            fromFactor = 1.0
            toFactor = 1.0
        } else if (fromFactor == null) {
            v = remoteConverterService.convert(from, millimeterUnit, value)
            fromFactor = 1.0
        } else if (toFactor == null) {
            v = (v * fromFactor)
            v = remoteConverterService.convert(millimeterUnit, to, v)
            fromFactor = 1.0
            toFactor = 1.0
        }

        val resultValue = (v * fromFactor) / toFactor!!
        log.debug("{} {} is equal to {} {}", value, from, resultValue, to)
        return resultValue
    }
}