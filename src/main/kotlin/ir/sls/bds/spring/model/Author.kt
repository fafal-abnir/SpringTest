package ir.sls.bds.spring.model


import ir.sls.bds.spring.dto.AuthorDto
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.io.Serializable
import javax.persistence.*

@Entity
//@Cache(region="common", usage = CacheConcurrencyStrategy.READ_ONLY)

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="AuthorRegion")
@Table(name = "author")
class Author(fName: String, lName: String, authorBooks: MutableSet<Book>? = null) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1

    @Column(name = "firstname")
    var firstName: String = fName

    @Column(name = "lastname")
    var lastName: String = lName

    //    @ManyToMany(cascade = [CascadeType.ALL])
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(mappedBy = "authors")
//    @JoinTable
    var books: MutableSet<Book>? = authorBooks

    fun toDto(): AuthorDto {
        return AuthorDto(this.firstName, this.lastName, books?.map { it.name })

    }
    constructor(): this("","",null)
}
