package ir.sls.bds.spring.dto

import java.io.Serializable

data class EmployeeDto(val id:Long,val fName:String,val lName:String,val email:String,val phone:String) : Serializable