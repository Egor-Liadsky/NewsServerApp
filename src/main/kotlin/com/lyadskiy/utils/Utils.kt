package com.lyadskiy.utils

object Utils {
    fun pagination(pageSize: Long, page: Int): Long {
        return (page - 1) * pageSize
    }
}