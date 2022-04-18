package io.github.woodsmarshes.myappcancrash

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import io.github.woodsmarshes.myappcancrash.databinding.ActivityPackageSelectCardBinding

class PackageSelectCardActivity : AppCompatActivity() {

    companion object {
        const val PACKAGE_NAME = "packageName"
        const val PACKAGE_IMAGE_ID = "packageImageId"
    }
    lateinit var binding: ActivityPackageSelectCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackageSelectCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val packageName = intent.getStringExtra(PACKAGE_NAME)
        val packageImage = intent.getParcelableExtra<Bitmap>(PACKAGE_IMAGE_ID)
        Glide.with(MyAppCanCrash.context).load(packageImage).into(binding.image)
        binding.textView.text = packageName
    }
}