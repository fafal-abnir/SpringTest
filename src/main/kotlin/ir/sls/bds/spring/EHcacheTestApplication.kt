package ir.sls.bds.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class EHcacheTestApplication

fun main(args: Array<String>) {
	runApplication<EHcacheTestApplication>(*args)
}
