package io.github.woodsmarshes.myappcancrash.ui.packagelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.woodsmarshes.myappcancrash.MyAppCanCrash
import io.github.woodsmarshes.myappcancrash.logic.Repository
import io.github.woodsmarshes.myappcancrash.logic.model.Package

class PackageListViewModel: ViewModel(){
      // private val packageDao = PackageDatabase.getDatabase(MyAppCanCrash.context).packageDao()
      val searchLiveData = MutableLiveData<String>()
      val placeList = ArrayList<Package>()
     //val placeLiveData = Transformations.switchMap(searchLiveData) { packageName ->
     //     Repository.getPackage(packageName)
     // }
    fun getPackage(packageName: String) {
        searchLiveData.value = packageName
    }


    var packageList = ArrayList<Package>()
    fun setPackageList() {
        packageList = Repository.getPackage()
    }
}