package io.github.woodsmarshes.myappcancrash

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import io.github.woodsmarshes.myappcancrash.databinding.ActivityMainBinding
import io.github.woodsmarshes.myappcancrash.logic.dao.SearchPackage
import io.github.woodsmarshes.myappcancrash.logic.model.Package
import io.github.woodsmarshes.myappcancrash.ui.packagelist.MyObserver
import io.github.woodsmarshes.myappcancrash.ui.packagelist.PackageListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PackageListViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyObserver())
        viewModel = ViewModelProvider(this).get(PackageListViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val intent = Intent(this,PackageListActivity::class.java)
            startActivity(intent)
        }
    }
}
//recyclerview适配器
class PackageAdepter(val context: Context,val packageList: List<Package>,val fragmentManager: FragmentManager): RecyclerView.Adapter<PackageAdepter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val packageImage: ImageView = view.findViewById(R.id.packageImage)
        val packageName: TextView = view.findViewById(R.id.packageName)
        val packages: MaterialCardView = view.findViewById(R.id.packages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.package_item,parent,false)
        val viewHolder = ViewHolder(view)
        viewHolder.packages.setOnClickListener {
        /*    val position = viewHolder.adapterPosition
            val package1 = packageList[position]
            val intent = Intent(context,PackageSelectCardActivity::class.java).apply {
                putExtra(PackageSelectCardActivity.PACKAGE_NAME,package1.packageName)
                putExtra(PackageSelectCardActivity.PACKAGE_IMAGE_ID,package1.packageImage)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
         */
            val position = viewHolder.adapterPosition
            MyBottomSheetDialog(packageList,position).show(fragmentManager,MyBottomSheetDialog.TAG)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val packages = packageList[position]
        holder.packageImage.setImageBitmap(packages.packageImage)
        Glide.with(MyAppCanCrash.context).load(packages.packageImage).into(holder.packageImage)
        holder.packageName.text = packages.packageName
    }

    override fun getItemCount() = packageList.size
}

//BottomSheetDialogFragment底部弹窗
class MyBottomSheetDialog(val packageList: List<Package>,val position: Int) : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "MyBottomSheetDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.package_buttom_sheet_dialog, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        val package1 = packageList[position]
        if (view != null) {
            val viewHolder = ViewHolder(view)
            viewHolder.textCard.text = package1.packageName
            Glide.with(MyAppCanCrash.context).load(package1.packageImage).into(viewHolder.imageCard)
        }
    }

    inner class ViewHolder(view: View) {
        val imageCard: ImageView = view.findViewById(R.id.imageCard)
        val textCard: TextView = view.findViewById(R.id.textCard)
    }

}
