package it.iacovelli.funnyconverterbe.controller

import it.iacovelli.funnyconverterbe.exception.UnitConversionException
import it.iacovelli.funnyconverterbe.model.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Controller class for exception handling
 * @author Nicola Iacovelli
 * @version 1.0
 */
open class BaseController {

    /**
     * Method which handle when UnitConversionException is thrown from somewhere in the code
     * @param e exception thrown
     */
    @ExceptionHandler(
        UnitConversionException::class
    )
    fun handleConversionError(e: RuntimeException): BaseResponse<String?> {
        return BaseResponse(HttpStatus.BAD_REQUEST.value(), null, e.message ?: "Something went wrong :(")
    }

}