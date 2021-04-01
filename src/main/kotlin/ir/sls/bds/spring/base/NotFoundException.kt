package ir.sls.bds.spring.base

class EmployeeNotFoundException(id:Long): BadRequestException("Employee $id not found",404,1001)