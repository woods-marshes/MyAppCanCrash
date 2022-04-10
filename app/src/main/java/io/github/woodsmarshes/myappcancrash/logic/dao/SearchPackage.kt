package io.github.woodsmarshes.myappcancrash.logic.dao

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import io.github.woodsmarshes.myappcancrash.MyAppCanCrash
import io.github.woodsmarshes.myappcancrash.logic.model.Package
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchPackage {
    private val packages = ArrayList<Package>()

        @JvmName("getPackages1")
        fun getPackages() :ArrayList<Package>{
             val packageManager: PackageManager = MyAppCanCrash.context.packageManager
             val intent = Intent()
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
             val applicationInfo: List<ApplicationInfo> = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES)
            //val job = Job()
            //val scope = CoroutineScope(job)
            //scope.launch { // 处理具体的逻辑
                for (i in applicationInfo.indices) {
                    //applicationInfo[i].loadLabel(packageManager).toString()
                    //applicationInfo[i].loadIcon(packageManager)
                    packages.add(Package(applicationInfo[i].loadLabel(packageManager).toString(),applicationInfo[i].loadIcon(packageManager)))
                }
            //}
            //job.cancel()
            return packages

        }
}





