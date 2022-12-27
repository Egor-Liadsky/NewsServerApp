package com.lyadskiy.database.dao.users

import com.lyadskiy.dto.User
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.dto.UserResponse


interface UserDAO {

    suspend fun getUserByLogin(login: String): UserResponse

    suspend fun registerUser(userReceive: User): Boolean
}