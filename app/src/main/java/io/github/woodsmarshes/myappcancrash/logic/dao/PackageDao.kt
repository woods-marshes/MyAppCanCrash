/*
package io.github.woodsmarshes.myappcancrash.logic.dao

import androidx.room.*
import io.github.woodsmarshes.myappcancrash.logic.model.Package

@Dao
interface PackageDao {
    @Insert
    fun insertPackage(packages: Package): Long
    @Update
    fun updatePackage(packages: Package)
    @Delete
    fun deletePackage(packages: Package)
    @Query("select * from Package")
    fun loadAllUsers(): List<Package>
    @Query("select * from Package where packageName = :packageName")
    fun loadPackages(packageName: String): List<Package>
}

 */