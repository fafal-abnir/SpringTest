package ir.sls.bds.spring.repository

import ir.sls.bds.spring.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(bookName:String) : Book
}