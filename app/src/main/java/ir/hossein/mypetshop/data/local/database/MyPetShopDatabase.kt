package ir.hossein.mypetshop.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hossein.mypetshop.data.local.dao.ProductDao
import ir.hossein.mypetshop.data.local.dao.UserDao
import ir.hossein.mypetshop.data.model.ProductDTO
import ir.hossein.mypetshop.data.model.UserDTO

@Database(entities = [UserDTO::class, ProductDTO::class], version = 1, exportSchema = false)
abstract class MyPetShopDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}