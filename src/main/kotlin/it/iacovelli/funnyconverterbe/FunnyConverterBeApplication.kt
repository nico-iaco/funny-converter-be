package it.iacovelli.funnyconverterbe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main class for spring boot applications
 */
@SpringBootApplication
class FunnyConverterBeApplication

/**
 * Main method from which the application is invoked
 * @param args arguments passed to application
 */
fun main(args: Array<String>) {
    runApplication<FunnyConverterBeApplication>(*args)
}
