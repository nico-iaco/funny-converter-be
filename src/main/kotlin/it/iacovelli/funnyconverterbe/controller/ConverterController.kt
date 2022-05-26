package it.iacovelli.funnyconverterbe.controller

import it.iacovelli.funnyconverterbe.model.BaseResponse
import it.iacovelli.funnyconverterbe.model.ConvertRequest
import it.iacovelli.funnyconverterbe.service.ConverterService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class ConverterController(val converterService: ConverterService) {

    val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/convert")
    fun convert(@RequestBody request: ConvertRequest): BaseResponse<Double> {
        log.debug("POST request /convert with body: {}", request)
        val value = converterService.convert(request.from, request.to, request.value)
        return BaseResponse(HttpStatus.OK, value, "")
    }

}