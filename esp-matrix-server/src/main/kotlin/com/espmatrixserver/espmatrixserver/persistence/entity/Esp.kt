package com.espmatrixserver.espmatrixserver.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Esp(
    @Id val macAddress: String,
    var ipAddress:String,
    var port: Int,
    var height: Short,
    var width: Short,
    @ManyToOne
    val owner: User,
    ){

}