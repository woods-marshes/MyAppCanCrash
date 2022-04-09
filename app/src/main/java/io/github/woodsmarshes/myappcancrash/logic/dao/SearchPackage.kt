package io.github.woodsmarshes.myappcancrash.logic.dao

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import io.github.woodsmarshes.myappcancrash.MyAppCanCrash
import io.github.woodsmarshes.myappcancrash.logic.model.Package

class SearchPackage {
    private val packageManager: PackageManager = MyAppCanCrash.context.packageManager
    private val applicationInfo: List<ApplicationInfo> = packageManager.getInstalledApplications(PackageManager. GET_SHARED_LIBRARY_FILES)
    private lateinit var packages: ArrayList<Package>

        fun getPackages (): ArrayList<Package> {
            for (i in 0 .. applicationInfo.size) {
                applicationInfo[i].loadLabel(packageManager).toString()
                applicationInfo[i].loadIcon(packageManager)
                packages.add(Package(applicationInfo[i].loadLabel(packageManager).toString(),applicationInfo[i].loadIcon(packageManager)))
            }
            return packages
        }
}