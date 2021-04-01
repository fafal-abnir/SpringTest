package ir.sls.bds.spring.base

import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class TestExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = KotlinLogging.logger {}

    companion object {
        const val ERROR_MESSAGE_KEY = "message"
        const val ERROR_CODE_KEY = "errorCode"
    }

    @ExceptionHandler(BadRequestException::class)
    protected fun handleConflict(badRequestException: BadRequestException,
                                 request: WebRequest): ResponseEntity<Any> {
        val internalServerError = HttpStatus.INTERNAL_SERVER_ERROR

        val (status, exception) = if (badRequestException.statusCode >= internalServerError.value()) {
            internalServerError to InternalServerError()
        } else {
            val status = try {
                HttpStatus.valueOf(badRequestException.statusCode)
            } catch (e: Exception) {
                log.error(e) {
                    "${badRequestException::class.simpleName} has incorrect statusCode: ${badRequestException.statusCode}"
                }

                internalServerError
            }

            status to badRequestException
        }

        val body = mapOf(ERROR_MESSAGE_KEY to exception.message,
                         ERROR_CODE_KEY to exception.errorCode)

        return handleExceptionInternal(exception, body, HttpHeaders(), status, request)
    }
}
