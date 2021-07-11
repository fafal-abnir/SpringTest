package ir.sls.bds.spring.controller

import com.google.gson.Gson
import ir.sls.bds.spring.model.Author
import ir.sls.bds.spring.model.Book
import ir.sls.bds.spring.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.*


@RestController
//@CacheConfig(cacheNames=["SimpleControllerCache"])
@RequestMapping("/v1")
class SimpleController(@Autowired val bookService: BookService) {
    var gson = Gson()

    //    var jsonString = gson.toJson(TestModel(1,"Test"))
    @GetMapping("/book/{name}")
    fun searchBook(@PathVariable name: String): String? {
        return gson.toJson(bookService.searchBook(name))
    }


    @GetMapping("/author/findall")
    fun findAll(): String {
        return gson.toJson(bookService.getAllAuthor())
    }

    @GetMapping("/author/{id}")
    fun fetchAuthorById(@PathVariable id:Long):String{
        return gson.toJson(bookService.searchAuthorById(id))
    }

//    @Caching(evict = {@CacheEvict(value = "allproductcache", allEntries = true),
//        @CacheEvict(value = "productcache", key = "#product.id")
//    })
    @Caching(evict = [CacheEvict(allEntries = true),
        CacheEvict(key = "#firstname")])
    @GetMapping("/searchbyfirstname/{firstname}")
    fun fetchDataByFirstName(@PathVariable firstname: String): String {
        return gson.toJson(bookService.searchAuthor(firstname))
    }

    @PostMapping("/createUser")
    fun createUser(@RequestBody author: Author): String {
        return gson.toJson(bookService.createAuthor(author))
    }

    @PostMapping("/createBook")
    fun createBook(@RequestBody book: Book): String {
        return gson.toJson(bookService.createBook(book))
    }

    @PostMapping("/createRandomAuthor")
    fun createRandomAuthor(): String {
        return gson.toJson(bookService.createRandomAuthor())
    }

    @PostMapping("/createRandomBook")
    fun createRandomBook(): String {
        return gson.toJson(bookService.createRandomBook())
    }

    @GetMapping("/getRandomAuthor")
    fun getRandomAuthor(): String {
        return gson.toJson(bookService.getRandomAuthor())
    }

    @GetMapping("/getRandomBook")
    fun getRandomBook(): String {
        String::class.java
        return gson.toJson(bookService.createRandomBook())
    }

    ///Employee

    @GetMapping("/employee/{id}")
    @Cacheable("employee")
    fun fetchEmployeeById(@PathVariable id:Long):String{
        return gson.toJson(bookService.getEmployeeWithId(id))
    }

    @GetMapping("/employee2/{id}")
    fun fetchDummyEmployee2ById(@PathVariable id:Long):String{
        return gson.toJson(bookService.getDummyEmployeeWithId(id))
    }

    @GetMapping("/allEmployee")
//    @Cacheable(value=["allEmployee"])
    fun getAllEmployees():String{
        return gson.toJson(bookService.getAllEmployees())
    }

    @GetMapping("/allOddEmployee")
    fun getAllOddEmployees():String{
        return gson.toJson(bookService.getOddEmployees())
    }

    @PostMapping("/createRandomEmployee")
    fun createRandomEmployee(): String {
        return gson.toJson(bookService.createRandomEmployee())
    }

    @PatchMapping("/employee/{id}")
    fun updateEmployeeById(@PathVariable id:Long):String{
        return gson.toJson(bookService.updateEmployeeWithId(id))
    }


    @DeleteMapping("/employee/{id}")
    fun deleteEmployeeById(@PathVariable id:Long):String{
        bookService.deleteEmployeeWithId(id)
        return "Employee with $id deleted."
    }

}