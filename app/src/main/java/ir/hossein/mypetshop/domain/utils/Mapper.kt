package ir.hossein.mypetshop.domain.utils

import ir.hossein.mypetshop.domain.model.Product as ProductDomain
import ir.hossein.mypetshop.data.model.Product as ProductData
import ir.hossein.mypetshop.data.model.User as UserData
import ir.hossein.mypetshop.domain.model.User as UserDomain

fun UserData.mapToDomain() = UserDomain(
    id = this.id,
    username = this.username,
    name = this.name,
    family = this.family,
    email = this.email,
    logged = this.logged,
    thumbnail = this.thumbnail
)

fun UserDomain.mapToData() = UserData(
    id = this.id,
    username = this.username,
    name = this.name,
    family = this.family,
    email = this.email,
    logged = this.logged,
    thumbnail = this.thumbnail
)

fun ProductData.mapToDomain() = ProductDomain(
    id = this.id,
    name = this.name,
    price = this.price,
    category = this.category,
    amount = this.amount
)

fun ProductDomain.mapToData() = ProductData(
    id = this.id,
    name = this.name,
    price = this.price,
    category = this.category,
    amount = this.amount
)