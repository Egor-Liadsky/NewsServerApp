package com.lyadskiy.database.dao.users

import com.lyadskiy.database.DatabaseFactory.dbQuery
import com.lyadskiy.database.entity.UsersEntity
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.dto.UserResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserDAOImpl : UserDAO {
    override suspend fun getUsers(): List<UserResponse> = dbQuery {
        return@dbQuery UsersEntity.selectAll().map {
            UserResponse(
                id = it[UsersEntity.rowId],
                username = it[UsersEntity.username],
                password = it[UsersEntity.password]
            )
        }
    }

    override suspend fun getUser(id: Int): UserResponse = dbQuery {
        val user = UsersEntity.select(UsersEntity.rowId eq id).single()
        return@dbQuery UserResponse(
            id = user[UsersEntity.rowId],
            username = user[UsersEntity.username],
            password = user[UsersEntity.password]
        )
    }

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
    override suspend fun updateUser(id: Int, userReceive: UserReceive) = dbQuery {
        UsersEntity.update({UsersEntity.rowId eq id}){
            it[username] = userReceive.username
            it[password] = userReceive.password
        }
        return@dbQuery
    }

    override suspend fun deleteUser(id: Int) = dbQuery {
        UsersEntity.deleteWhere { rowId eq id }
        return@dbQuery
    }
}