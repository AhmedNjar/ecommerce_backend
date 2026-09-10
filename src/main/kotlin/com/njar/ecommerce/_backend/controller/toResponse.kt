package com.njar.ecommerce._backend.controller

import com.njar.ecommerce._backend.database.model.Cloth

// خليها بَرّه الكلاس كدة
fun Cloth.toResponse(): ClothResponse {
    return ClothResponse(
        id = this.id.toHexString(),
        title = this.title,
        description = this.description,
        color = this.color,
        image = this.image,
        price = this.price,
        available = this.available,
        company = this.company,
        evaluation = this.evaluation,
        sharedAt = this.sharedAt,
        category = this.category
    )
}