/*
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.woodsmarshes.myappcancrash.MyAppCanCrash
import io.github.woodsmarshes.myappcancrash.logic.dao.PackageDao
import io.github.woodsmarshes.myappcancrash.logic.model.Package

@Database(version = 1, entities = [Package::class])
abstract class PackageDatabase : RoomDatabase() {
    abstract fun packageDao(): PackageDao

    companion object {
        private var instance: PackageDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): PackageDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                PackageDatabase::class.java,
                "package_database"
            )
                .build()
                .apply {
                    instance = this
                }
        }
    }
}

 */