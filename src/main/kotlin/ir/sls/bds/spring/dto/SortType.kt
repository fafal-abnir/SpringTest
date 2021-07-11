package ir.sls.bds.spring.dto

import java.io.Serializable

enum class SortType : Serializable {
    NEWEST,
    OLDEST,
    FAVOURITE,
    LIKE,
    NONE;
}