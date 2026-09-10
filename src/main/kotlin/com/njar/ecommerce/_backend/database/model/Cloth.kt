package com.njar.ecommerce._backend.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("njar")
data class Cloth(
    val title: String,
    val description: String,
    val color: Long,
    val image: String,
    val price: Int,
    val available: Boolean,
    val company: String,
    //val sizes: List<String>,
    val evaluation: Int,
    val sharedAt : Instant,
    //val ownerId: ObjectId,
    val category: String? = null,
    @Id val id: ObjectId = ObjectId.get(),
)

