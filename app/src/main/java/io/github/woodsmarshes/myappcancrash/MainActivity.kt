package io.github.woodsmarshes.myappcancrash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.woodsmarshes.myappcancrash.databinding.ActivityMainBinding
import io.github.woodsmarshes.myappcancrash.ui.packagelist.MyObserver
import io.github.woodsmarshes.myappcancrash.ui.packagelist.PackageListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PackageListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyObserver())
        viewModel = ViewModelProvider(this).get(PackageListViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            viewModel.getPackage(binding.editText.text.toString())
            }
        viewModel.searchLiveData.observe(this, Observer { count ->
            binding.textView.text = count.toString()
        })

    }
}