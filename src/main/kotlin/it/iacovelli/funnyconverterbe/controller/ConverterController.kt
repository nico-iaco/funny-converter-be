package it.iacovelli.funnyconverterbe.controller

import it.iacovelli.funnyconverterbe.model.BaseResponse
import it.iacovelli.funnyconverterbe.model.ConvertRequest
import it.iacovelli.funnyconverterbe.service.ConverterService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Controller class with the convert endpoint
 * @param converterService service class used to perform the conversion
 * @author Nicola Iacovelli
 * @version 1.0
 */
@RestController("/")
class ConverterController(val converterService: ConverterService) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Api exposed as POST used to convert from a unit to another
     * @param request containing the source unit and its value and destination unit to convert
     * @return a base response with body populated with the converted value
     */
    @PostMapping("/convert")
    fun convert(@RequestBody request: ConvertRequest): BaseResponse<Double> {
        log.debug("POST request /convert with body: {}", request)
        val value = converterService.convert(request.from, request.to, request.value)
        return BaseResponse(HttpStatus.OK, value, "")
    }

}