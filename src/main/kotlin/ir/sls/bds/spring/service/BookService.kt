package ir.sls.bds.spring.service

import ir.sls.bds.spring.base.EmployeeNotFoundException
import ir.sls.bds.spring.dto.AuthorDto
import ir.sls.bds.spring.dto.BookDto
import ir.sls.bds.spring.dto.EmployeeDto
import ir.sls.bds.spring.model.Author
import ir.sls.bds.spring.model.Book
import ir.sls.bds.spring.model.Category
import ir.sls.bds.spring.model.Employee
import ir.sls.bds.spring.repository.AuthorRepository
import ir.sls.bds.spring.repository.BookRepository
import ir.sls.bds.spring.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookService(@Autowired val bookRepository: BookRepository,
                  val authorRepository: AuthorRepository,val employeeRepository: EmployeeRepository
                    ) {

    fun searchBook(name: String): BookDto {
        return bookRepository.findByName(name).toDto()
    }

    fun getAllAuthor(): List<AuthorDto> {
        return authorRepository.findAll().map { it.toDto() }
    }

    fun searchAuthorById(id:Long): AuthorDto? {
        return authorRepository.findById(id).orElse(null).toDto()
    }

    fun searchAuthor(firstname: String): List<AuthorDto> {
        return authorRepository.findByFirstName(firstname).map { it.toDto() }
    }

    fun createAuthor(author: Author): AuthorDto {
        return authorRepository.save(author).toDto()
    }

    fun createBook(book: Book): BookDto {
        return bookRepository.save(book).toDto()
    }

    fun createRandomAuthor(): AuthorDto {
        val randomNumber = (10000..99999).random()
        val author = Author("name_${randomNumber}", "last_${randomNumber}")
        return authorRepository.save(author).toDto()
    }

    fun createRandomBook(): BookDto {
        val randomNumber = (10000..99999).random()
        val randomCategory = Category.values().toList().shuffled().first()
        val randomAuthors = mutableSetOf(authorRepository.findAll().random())
        val book = Book("name_${randomNumber}", randomCategory, randomAuthors)
        return bookRepository.save(book).toDto()

    }

    fun getRandomAuthor(): AuthorDto {
        return authorRepository.findAll().random().toDto()
    }

    fun getRandomBook(): BookDto {
        return bookRepository.findAll().random().toDto()
    }


    /////Employee
    fun getAllEmployees():List<EmployeeDto>{
        return employeeRepository.findAll().map { it.toDto() }
    }

    fun getOddEmployees():List<EmployeeDto>{
        return employeeRepository.findAllOddEmployee().map { it.toDto() }
    }

    fun createRandomEmployee(): EmployeeDto {
        val randomNumber = (10000..99999).random()
        val employee = Employee("fname_${randomNumber}", "lname_${randomNumber}", "mail_${randomNumber}","phone_${randomNumber}")
        return employeeRepository.save(employee).toDto()
    }

    fun getEmployeeWithId(id: Long): EmployeeDto {
        return employeeRepository.findById(id).orElse(null)?.toDto()
                ?: throw EmployeeNotFoundException(id)
    }

    fun updateEmployeeWithId(id:Long):EmployeeDto{
        val randomNumber = (10000..99999).random()
        val employee = employeeRepository.findById(id).orElse(null)
                ?: throw EmployeeNotFoundException(id)
        employee.firstName = "fname_${randomNumber}"
        return employeeRepository.save(employee).toDto()
    }

    fun deleteEmployeeWithId(id: Long){
        employeeRepository.deleteById(id)
    }
}