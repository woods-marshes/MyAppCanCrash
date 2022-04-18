package io.github.woodsmarshes.myappcancrash.logic.model

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
data class Package(val packageName: String,val packageImage: Bitmap)
