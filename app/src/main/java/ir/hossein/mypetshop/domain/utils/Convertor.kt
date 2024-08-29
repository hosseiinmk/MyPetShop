package ir.hossein.mypetshop.domain.utils

import ir.hossein.mypetshop.data.model.ProductDTO
import ir.hossein.mypetshop.data.model.UserDTO
import ir.hossein.mypetshop.domain.model.Product
import ir.hossein.mypetshop.domain.model.User

fun UserDTO.mapToDomain() = User(
    id = this.id,
    username = this.username,
    name = this.name,
    family = this.family,
    email = this.email,
    logged = this.logged,
    thumbnail = this.thumbnail
)

fun User.mapToData() = UserDTO(
    id = this.id,
    username = this.username,
    name = this.name,
    family = this.family,
    email = this.email,
    logged = this.logged,
    thumbnail = this.thumbnail
)

fun ProductDTO.mapToDomain() = Product(
    id = this.id,
    name = this.name,
    price = this.price,
    category = this.category,
    amount = this.amount,
    imagePath = this.imagePath
)

fun Product.mapToData() = ProductDTO(
    id = this.id,
    name = this.name,
    price = this.price,
    category = this.category,
    amount = this.amount,
    imagePath = this.imagePath
)