package it.iacovelli.funnyconverterbe.model

import org.springframework.http.HttpStatus

class BaseResponse<T>(var statusCode: HttpStatus, var body: T, var message: String)