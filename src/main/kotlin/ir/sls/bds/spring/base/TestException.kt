package ir.sls.bds.spring.base


/**
 * Parent of all exceptions in Sheyvash project.
 *
 * All of exceptions should inherit from this class.
 */
open class TestException : Exception {
    val errorCode: Int

    constructor(message: String, errorCode: Int) : super(message) {
        this.errorCode = errorCode
    }

    constructor(message: String, errorCode: Int, cause: Throwable) : super(message, cause) {
        this.errorCode = errorCode
    }
}
