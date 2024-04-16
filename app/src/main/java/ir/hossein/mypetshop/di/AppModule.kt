package ir.hossein.mypetshop.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hossein.mypetshop.data.local.dao.ProductDao
import ir.hossein.mypetshop.data.local.dao.UserDao
import ir.hossein.mypetshop.data.local.database.MyPetShopDatabase
import ir.hossein.mypetshop.ui.utils.Constant
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): MyPetShopDatabase = Room.databaseBuilder(
        context = application,
        klass = MyPetShopDatabase::class.java,
        name = Constant.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideUserDao(db: MyPetShopDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideProductDao(db: MyPetShopDatabase): ProductDao = db.productDao()
}