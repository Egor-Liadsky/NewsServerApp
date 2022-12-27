package com.lyadskiy.database.dao.users

import com.lyadskiy.database.DatabaseFactory.dbQuery
import com.lyadskiy.database.entity.UsersEntity
import com.lyadskiy.dto.User
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.dto.UserResponse
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDAOImpl : UserDAO {

    override suspend fun getUserByLogin(login: String): UserResponse = dbQuery {
        val usersEntity = UsersEntity.select { UsersEntity.login eq login }.single()
        return@dbQuery UserResponse(
            id = usersEntity[UsersEntity.rowId],
            login = usersEntity[UsersEntity.login],
            password = usersEntity[UsersEntity.password],
            salt = usersEntity[UsersEntity.salt]
        )
    }

    override suspend fun registerUser(user: User): Boolean = dbQuery {
        UsersEntity.insert {
            it[login] = user.login
            it[password] = user.password
            it[salt] = user.salt
        }
        return@dbQuery true
    }
}