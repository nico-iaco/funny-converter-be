package it.iacovelli.funnyconverterbe.model

import org.springframework.http.HttpStatus

/**
 * Base response of the application to send to frontend applications
 * @param statusCode of the response
 * @param body populated with the requested value
 * @param message populated with the occurred error empty string otherwise
 * @author Nicola Iacovelli
 * @version 1.0
 */
class BaseResponse<T>(var statusCode: Int, var body: T, var message: String)