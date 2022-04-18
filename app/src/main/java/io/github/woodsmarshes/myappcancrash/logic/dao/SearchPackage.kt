package io.github.woodsmarshes.myappcancrash.logic.dao

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import io.github.woodsmarshes.myappcancrash.MyAppCanCrash
import io.github.woodsmarshes.myappcancrash.logic.model.Package


class SearchPackage {
    private val packages = ArrayList<Package>()

        @JvmName("getPackages1")
        suspend fun getPackages() :ArrayList<Package>{
             val packageManager: PackageManager = MyAppCanCrash.context.packageManager
             val intent = Intent()
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
             val applicationInfo: List<ApplicationInfo> = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES)
                for (i in applicationInfo.indices) {
                    //applicationInfo[i].loadLabel(packageManager).toString()
                    val drawable = applicationInfo[i].loadIcon(packageManager)
                    val bitmap = Bitmap.createBitmap(
                        drawable.intrinsicWidth, drawable.intrinsicHeight,
                        if (drawable.opacity !== PixelFormat.OPAQUE)
                            Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
                    )
                    val canvas = Canvas(bitmap)
                    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                    drawable.draw(canvas)
                    packages.add(Package(applicationInfo[i].loadLabel(packageManager).toString(),bitmap))
                }
            return packages

        }
}





