package io.github.woodsmarshes.myappcancrash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.github.woodsmarshes.myappcancrash.databinding.ActivityPackageListBinding
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.woodsmarshes.myappcancrash.logic.dao.SearchPackage
import io.github.woodsmarshes.myappcancrash.logic.model.Package
import io.github.woodsmarshes.myappcancrash.ui.packagelist.MyObserver
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class PackageListActivity : AppCompatActivity(),CoroutineScope by MainScope(){

    lateinit var binding : ActivityPackageListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackageListBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val fragmentManager: FragmentManager = supportFragmentManager
            launch {
                val packageLists = withContext(Dispatchers.IO) {
                    SearchPackage().getPackages()
                }
                binding.progressBar.visibility = View.GONE
                binding.RecyclerView.visibility = View.VISIBLE
                val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                binding.RecyclerView.layoutManager = layoutManager
                val adepter = PackageAdepter(MyAppCanCrash.context,packageLists,fragmentManager)
                binding.RecyclerView.adapter = adepter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


