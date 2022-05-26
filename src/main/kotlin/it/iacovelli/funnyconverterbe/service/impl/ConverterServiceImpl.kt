package it.iacovelli.funnyconverterbe.service.impl

import it.iacovelli.funnyconverterbe.config.ConversionConfig
import it.iacovelli.funnyconverterbe.service.ConverterService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ConverterServiceImpl(val conversionConfig: ConversionConfig) : ConverterService {

    val log = LoggerFactory.getLogger(this.javaClass)

    override fun convert(from: String, to: String, value: Double): Double {
        val fromFactor = conversionConfig.conversionFactorsMap[from]!!
        val toFactor = conversionConfig.conversionFactorsMap[to]!!
        val resultValue = (value * fromFactor) / toFactor
        log.debug("{} {} is equal to {} {}", value, from, resultValue, to)
        return resultValue
    }
}