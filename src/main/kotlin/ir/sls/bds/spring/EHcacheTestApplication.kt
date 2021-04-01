package ir.sls.bds.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EHcacheTestApplication

fun main(args: Array<String>) {
	runApplication<EHcacheTestApplication>(*args)
}
