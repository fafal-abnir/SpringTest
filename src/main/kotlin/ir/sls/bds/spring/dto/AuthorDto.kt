package ir.sls.bds.spring.dto

data class AuthorDto(val firstName:String,val lastName:String,val bookList:List<String>?){
    constructor(): this("", "",null)
}