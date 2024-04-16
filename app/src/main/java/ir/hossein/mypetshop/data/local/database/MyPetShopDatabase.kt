package ir.hossein.mypetshop.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hossein.mypetshop.data.local.dao.ProductDao
import ir.hossein.mypetshop.data.local.dao.UserDao
import ir.hossein.mypetshop.data.model.Product
import ir.hossein.mypetshop.data.model.User

@Database(entities = [User::class, Product::class], version = 1, exportSchema = false)
abstract class MyPetShopDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}