package it.iacovelli.funnyconverterbe.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ConversionConfig {

    @Value("#{\${it.iacovelli.conversion-factor}}")
    lateinit var conversionFactorsMap: Map<String, Double>

}