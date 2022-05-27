package it.iacovelli.funnyconverterbe.service.impl

import it.iacovelli.funnyconverterbe.exception.UnitConversionException
import it.iacovelli.funnyconverterbe.service.RemoteConverterService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

/**
 * Implementation of RemoteConverterService interface
 * @author Nicola Iacovelli
 * @version 1.0
 */
@Service
class RemoteConverterServiceImpl(val restTemplate: RestTemplate) : RemoteConverterService {

    @Value("\${it.iacovelli.remote-api.conversion-url}")
    lateinit var remoteUrl: String


    /**
     * Implementation method of RemoteConversionService interface
     */
    @Throws(UnitConversionException::class)
    override fun convert(from: String, to: String, value: Double): Double {
        val url = "${remoteUrl}/${from}/${value}"
        var responseMap: MutableMap<String, Double>
        try {
            val responseEntity = restTemplate.getForEntity(url, Map::class.java)
            if (!responseEntity.statusCode.is2xxSuccessful) {
                throw UnitConversionException("Trying to convert an unknown unit or the destination unit was not found")
            }
            responseMap = responseEntity.body as MutableMap<String, Double>
        } catch (e: RestClientException) {
            throw UnitConversionException(e.message ?: "Trying to convert an unknown unit or the destination unit was not found")
        }
        return responseMap[to]
            ?: throw UnitConversionException("Trying to convert an unknown unit or the destination unit was not found")
    }

}