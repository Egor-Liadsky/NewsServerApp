package com.lyadskiy.database.dao.users

import com.lyadskiy.dto.UserReceive
import com.lyadskiy.dto.UserResponse

interface UserDAO {

    suspend fun registerUser(userReceive: UserReceive)

    suspend fun authUser(userReceive: UserReceive): UserResponse
}