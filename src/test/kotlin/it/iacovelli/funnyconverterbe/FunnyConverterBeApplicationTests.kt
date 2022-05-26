package it.iacovelli.funnyconverterbe

import it.iacovelli.funnyconverterbe.model.ConvertRequest
import it.iacovelli.funnyconverterbe.service.ConverterService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FunnyConverterBeApplicationTests {

    @Autowired
    lateinit var converterService: ConverterService

    @Test
    fun contextLoads() {
    }

    @Test
    fun checkCorrectOliveConversion() {
        val request = ConvertRequest(
            from = "meter",
            to = "olive",
            value = 1.85
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 66.07142857142857)
    }

    @Test
    fun checkCorrectMeterConversion() {
        val request = ConvertRequest(
            from = "olive",
            to = "meter",
            value = 66.07142857142857
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 1.85)
    }

    @Test
    fun checkSameUnitConversion() {
        val request = ConvertRequest(
            from = "meter",
            to = "meter",
            value = 1.85
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 1.85)
    }

    @Test
    fun checkCorrectMillimeterConversion() {
        val request = ConvertRequest(
            from = "meter",
            to = "millimeter",
            value = 1.85
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 1850.0)
    }

}
