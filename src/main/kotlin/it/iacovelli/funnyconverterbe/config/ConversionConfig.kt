package it.iacovelli.funnyconverterbe.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 * Config class containing the map with the conversion's factor of the various unit towards millimeter
 * @author Nicola Iacovelli
 * @version 1.0
 */
@Configuration
class ConversionConfig {

    @Value("#{\${it.iacovelli.conversion-factor}}")
    lateinit var conversionFactorsMap: Map<String, Double>

}