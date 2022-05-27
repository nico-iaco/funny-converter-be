package it.iacovelli.funnyconverterbe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean

import org.springframework.web.client.RestTemplate




/**
 * Main class for spring boot applications
 */
@SpringBootApplication
class FunnyConverterBeApplication {

    /**
     * RestTemplate bean used to make calls to remote apis
     */
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

}

/**
 * Main method from which the application is invoked
 * @param args arguments passed to application
 */
fun main(args: Array<String>) {
    runApplication<FunnyConverterBeApplication>(*args)
}

