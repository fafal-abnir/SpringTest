package ir.sls.bds.spring.model

import ir.sls.bds.spring.dto.BookDto
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import javax.persistence.*


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "book")
class Book(bookName: String, category: Category, bookAuthors: MutableSet<Author>?) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var name: String = bookName

    @Column
    var category: String = category.name

    @Column
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany
//    @JoinTable(name = "author_book", joinColumns = [JoinColumn(name = "book_id")],
//               inverseJoinColumns = [JoinColumn(name = "author_id")])
    var authors: MutableSet<Author>? = bookAuthors

    fun toDto(): BookDto {
        val entity = this
        return BookDto(this.name, this.category, authors?.map { it.firstName to it.lastName })

    }
    constructor(): this("",Category.Science,null)
}

enum class Category {
    Science,
    History,
    Chemistry,
    Math
}