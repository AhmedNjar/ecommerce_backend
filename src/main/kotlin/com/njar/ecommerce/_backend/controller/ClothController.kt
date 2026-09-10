package com.njar.ecommerce._backend.controller

import com.njar.ecommerce._backend.controller.ClothController
import com.njar.ecommerce._backend.database.model.Cloth
import com.njar.ecommerce._backend.database.model.User
import com.njar.ecommerce._backend.database.repository.ClothRepository
import org.bson.types.ObjectId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.time.Instant
import kotlin.String

data class ClothRequest(
    val id: String? = null,        // خليه اختياري عشان لو منتج جديد لسه ملوش ID
    val title: String,
    val description: String,
    val color: Long,               // بيمثل لون الـ Hex أو الـ Long
    val image: String,             // رابط الصورة (URL)
    val price: Int,
    val available: Boolean,
    val company: String,
    val evaluation: Int,
    val category: String?          // التصنيف (مثلاً Jacket, T-shirt)
)

data class ClothResponse(
    val id: String,                // هنا الـ ID إجباري لأنه راجع من الـ DB
    val title: String,
    val description: String,
    val color: Long,
    val image: String,
    val price: Int,
    val available: Boolean,
    val company: String,
    val evaluation: Int,
    val sharedAt: Instant,         // السيرفر بيضيف الوقت اللي المنتج اتسيف فيه
    val category: String?
)

data class ClothListResponse(
    val clothes: List<ClothResponse>
)

@RestController
@RequestMapping("/clothes")
class ClothController(private val repository: ClothRepository) {

    @PostMapping
    fun save(@RequestBody body: ClothRequest): ClothResponse {
        val cloth = repository.save(
            Cloth(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                description = body.description,
                color = body.color,
                image = body.image,
                price = body.price,
                available = body.available,
                company = body.company,
                evaluation = body.evaluation,
                sharedAt = Instant.now(),
                category = body.category
            )
        )
        return cloth.toResponse()
    }

    @GetMapping
    fun getAllClothes(): ClothListResponse {
        val all = repository.findAll().map { it.toResponse() }
        return ClothListResponse(clothes = all) // كدة الأندرويد هيفهمه فوراً
    }

    @GetMapping("/available")
    fun findByAvailable(@RequestParam(defaultValue = "true") available: Boolean): ClothListResponse {
        val filtered = repository.findByAvailable(available).map { it.toResponse() }
        return ClothListResponse(clothes = filtered)
    }

    @GetMapping("/search")
    fun findByTitle(@RequestParam title: String): ClothListResponse {
        val results = repository.findByTitle(title).map { it.toResponse() }
        return ClothListResponse(clothes = results)
    }
}