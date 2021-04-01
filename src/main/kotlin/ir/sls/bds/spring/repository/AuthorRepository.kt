package ir.sls.bds.spring.repository

import ir.sls.bds.spring.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface AuthorRepository : JpaRepository<Author, Long> {
    fun findByFirstName(FirstName: String?): List<Author>
    override fun findById(id: Long): Optional<Author>
    override fun findAll(): List<Author>

}