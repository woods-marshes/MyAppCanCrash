package io.github.woodsmarshes.myappcancrash.logic.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
data class Package(var packageName: String,var packageImage: Drawable) {

}
