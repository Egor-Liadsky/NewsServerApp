package com.lyadskiy.database.dao.users

import com.lyadskiy.dto.UserReceive
import com.lyadskiy.dto.UserResponse

interface UserDAO {

    suspend fun getUsers(): List<UserResponse>

    suspend fun getUser(id: Int): UserResponse

    suspend fun registerUser(userReceive: UserReceive)

    suspend fun authUser(userReceive: UserReceive): UserResponse

    suspend fun deleteUser(id: Int)

    suspend fun updateUser(id: Int, userReceive: UserReceive)
}