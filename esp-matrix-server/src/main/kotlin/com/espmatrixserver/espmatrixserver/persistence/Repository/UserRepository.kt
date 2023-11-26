package com.espmatrixserver.espmatrixserver.persistence.Repository

import com.espmatrixserver.espmatrixserver.persistence.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>, CrudRepository<User, String> {
    fun getUserByName(name: String): User?
}