package com.espmatrixserver.espmatrixserver.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class User(
    @Id
    val name: String
) {
    val email: String = ""
    val password: String = ""
    val id: Long = 0
}