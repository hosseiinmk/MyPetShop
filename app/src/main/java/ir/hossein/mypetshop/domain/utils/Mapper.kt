package ir.hossein.mypetshop.domain.utils

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