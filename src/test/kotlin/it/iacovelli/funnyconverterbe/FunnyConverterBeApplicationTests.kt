package it.iacovelli.funnyconverterbe

import it.iacovelli.funnyconverterbe.exception.UnitConversionException
import it.iacovelli.funnyconverterbe.model.ConvertRequest
import it.iacovelli.funnyconverterbe.service.ConverterService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Tests class for unit test
 * @author Nicola Iacovelli
 * @version 1.0
 */
@SpringBootTest
class FunnyConverterBeApplicationTests {

    @Autowired
    lateinit var converterService: ConverterService

    val meter = "meter"

    val olive = "olive"

    val millimeter = "millimeter"

    val wrongUnit = "fakeUnit"

    /**
     * Tests if the application starts
     */
    @Test
    fun contextLoads() {
    }

    /**
     * Tests if the conversion meter -> olive is done correctly
     */
    @Test
    fun checkCorrectOliveConversion() {
        val request = ConvertRequest(
            from = meter,
            to = olive,
            value = 1.85
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 66.07142857142857)
    }

    /**
     * Tests if the conversion olive -> meter is done correctly
     */
    @Test
    fun checkCorrectMeterConversion() {
        val request = ConvertRequest(
            from = olive,
            to = meter,
            value = 66.07142857142857
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 1.85)
    }

    /**
     * Tests if the conversion meter -> meter is done correctly
     */
    @Test
    fun checkSameUnitConversion() {
        val request = ConvertRequest(
            from = meter,
            to = meter,
            value = 1.85
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 1.85)
    }

    /**
     * Tests if the conversion meter -> millimeter is done correctly
     */
    @Test
    fun checkCorrectMillimeterConversion() {
        val request = ConvertRequest(
            from = meter,
            to = millimeter,
            value = 1.85
        )
        val convertedValue = converterService.convert(request.from, request.to, request.value)
        assert(convertedValue == 1850.0)
    }

    @Test
    fun checkExceptionThrownIfUnitIsIncorrect() {
        val request = ConvertRequest(
            from = wrongUnit,
            to = millimeter,
            value = 23.7
        )
        assertThrows<UnitConversionException> { converterService.convert(request.from, request.to, request.value) }
    }

}
