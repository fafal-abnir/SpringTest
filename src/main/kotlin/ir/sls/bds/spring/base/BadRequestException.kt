package ir.sls.bds.spring.base

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Parent of all exceptions that is sent to the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class BadRequestException : TestException {
    val statusCode: Int

    constructor(message: String, statusCode: Int, errorCode: Int) : super(message, errorCode) {
        this.statusCode = statusCode
    }

    constructor(message: String, statusCode: Int, errorCode: Int, cause: Throwable)
            : super(message, errorCode, cause) {
        this.statusCode = statusCode
    }
}