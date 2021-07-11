package ir.sls.bds.spring.repository

import ir.sls.bds.spring.model.Employee
import org.hibernate.jpa.QueryHints.HINT_CACHEABLE
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import javax.persistence.QueryHint


interface EmployeeRepository : JpaRepository<Employee, Long> {
    @QueryHints(QueryHint(name = HINT_CACHEABLE, value = "true"))
    //@Where(clause = "id < 4")
    override fun findAll(): MutableList<Employee>

    @QueryHints(QueryHint(name = HINT_CACHEABLE, value = "true"))
    @Query("Select e From Employee e where e.id%2=1 ")
    fun findAllOddEmployee(): MutableList<Employee>
}