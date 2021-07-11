package ir.sls.bds.spring.model

import ir.sls.bds.spring.dto.EmployeeDto
import org.hibernate.annotations.Where
import java.io.Serializable
import javax.persistence.*

@Entity

//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "employee")
@Where(clause = "")
abstract class Person(fName: String, lName: String, mail:String,phoneN:String) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var firstName: String = fName

    @Column
    var lastName: String = lName

    @Column
    var email:String = mail

    @Column
    var phoneNumber:String = phoneN
    constructor(): this("","","","")
}