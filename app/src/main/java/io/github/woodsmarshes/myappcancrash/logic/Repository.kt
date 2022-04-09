package io.github.woodsmarshes.myappcancrash.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData
import io.github.woodsmarshes.myappcancrash.MyAppCanCrash
//import io.github.woodsmarshes.myappcancrash.logic.dao.PackageDao
import io.github.woodsmarshes.myappcancrash.logic.dao.SearchPackage
import io.github.woodsmarshes.myappcancrash.logic.model.Package
import kotlin.concurrent.thread

object Repository {

   // private val packageDao = PackageDatabase.getDatabase(MyAppCanCrash.context).packageDao()
    lateinit var packages: ArrayList<Package>

    fun savePackages() {
        thread {
            packages = SearchPackage().getPackages()
           /* for (i in 0 .. packages.size) {
                packageDao.insertPackage(packages[i])
            }

            */
        }
    }

    fun getPackage(packageName: String): LiveData<Package> {
        savePackages()
       /* val package1 = packageDao.loadPackages(packageName)
        val liveData = MutableLiveData<Package>()
        liveData.value = package1[0]

        */
        val liveData = MutableLiveData<Package>()
        liveData.value = packages[6]
        return liveData
    }



}