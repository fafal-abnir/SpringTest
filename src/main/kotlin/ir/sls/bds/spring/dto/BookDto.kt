package ir.sls.bds.spring.dto

data class BookDto(val bookName:String,val category: String,val authorList:List<Pair<String,String>>?)