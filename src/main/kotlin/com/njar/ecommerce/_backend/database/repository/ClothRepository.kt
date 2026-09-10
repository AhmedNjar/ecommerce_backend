package com.njar.ecommerce._backend.database.repository

import com.njar.ecommerce._backend.database.model.Cloth
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ClothRepository : MongoRepository<Cloth,ObjectId> {

    fun findByTitle(title: String): List<Cloth>
    fun findByAvailable(available: Boolean): List<Cloth>
}