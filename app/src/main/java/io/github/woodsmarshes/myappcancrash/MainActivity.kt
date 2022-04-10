package io.github.woodsmarshes.myappcancrash

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import io.github.woodsmarshes.myappcancrash.databinding.ActivityMainBinding
import io.github.woodsmarshes.myappcancrash.logic.dao.SearchPackage
import io.github.woodsmarshes.myappcancrash.logic.model.Package
import io.github.woodsmarshes.myappcancrash.ui.packagelist.MyObserver
import io.github.woodsmarshes.myappcancrash.ui.packagelist.PackageListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PackageListViewModel
    private lateinit var packages: ArrayList<Package>
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyObserver())
        viewModel = ViewModelProvider(this).get(PackageListViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setPackageList()
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.RecyclerView.layoutManager = layoutManager
        val adepter = PackageAdepter(this,viewModel.packageList)
        binding.RecyclerView.adapter = adepter
        /*val job = Job()
        val scope = CoroutineScope(job)
        scope.launch {
            while (true) {
                //SearchPackage().getPackages()
                adepter.notifyDataSetChanged()
            }
        }
        job.cancel()*/
        binding.button.setOnClickListener {
//            viewModel.getPackage(binding.editText.text.toString())
        }
        /* viewModel.searchLiveData.observe(this, Observer { count ->
            binding.textView.text = count.toString()
        })

         */


    }
}
class PackageAdepter(val context: Context, val packageList: List<Package>): RecyclerView.Adapter<PackageAdepter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val packageImage: ImageView = view.findViewById(R.id.packageImage)
        val packageName: TextView = view.findViewById(R.id.packageName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.package_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val packages = packageList[position]
        holder.packageImage.setImageDrawable(packages.packageImage)
        Glide.with(context).load(packages.packageImage).into(holder.packageImage)
        holder.packageName.text = packages.packageName

    }

    override fun getItemCount() = packageList.size
}