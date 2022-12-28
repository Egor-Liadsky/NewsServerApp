package com.lyadskiy.database.dao.users

import com.lyadskiy.database.DatabaseFactory.dbQuery
import com.lyadskiy.database.entity.UsersEntity
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.dto.UserResponse
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDAOImpl : UserDAO {
    override suspend fun registerUser(userReceive: UserReceive) = dbQuery {
        UsersEntity.insert {
            it[username] = userReceive.username
            it[password] = userReceive.password
        }
        return@dbQuery
    }

    override suspend fun authUser(userReceive: UserReceive): UserResponse = dbQuery {
        val user = UsersEntity.select { UsersEntity.username eq userReceive.username }.single()
        return@dbQuery UserResponse(
            id = user[UsersEntity.rowId],
            username = user[UsersEntity.username],
            password = user[UsersEntity.password]
        )
    }
}