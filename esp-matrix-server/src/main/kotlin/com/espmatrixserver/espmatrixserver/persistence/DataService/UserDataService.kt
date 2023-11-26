package com.espmatrixserver.espmatrixserver.persistence.DataService

import com.espmatrixserver.espmatrixserver.persistence.Repository.UserRepository
import com.espmatrixserver.espmatrixserver.persistence.entity.User
import org.springframework.stereotype.Service

@Service
class UserDataService(val userRepository: UserRepository) {
    fun loadDefaultUser(): User {
        val user = userRepository.getUserByName("default")
        if (user != null) {
            return user
        }
        return userRepository.save(User("default"))
    }
}