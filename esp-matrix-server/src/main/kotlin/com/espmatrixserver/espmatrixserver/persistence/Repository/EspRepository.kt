package com.espmatrixserver.espmatrixserver.persistence.Repository

import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EspRepository : JpaRepository<Esp, String>, CrudRepository<Esp, String> {
    fun getByMacAddress(macAddress: String): Esp?
}