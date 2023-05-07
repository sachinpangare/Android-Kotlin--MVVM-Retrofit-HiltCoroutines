package com.retrofitwithcoroutines.ui.main.view
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.retrofitwithcoroutines.R
import com.retrofitwithcoroutines.data.model.User
import com.retrofitwithcoroutines.databinding.ActivityMainBinding
import com.retrofitwithcoroutines.ui.main.adapter.UserAdapter
import com.retrofitwithcoroutines.ui.main.viewmodel.UserViewModel
import com.retrofitwithcoroutines.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var view: UserViewModel
    private lateinit var userAdapter : UserAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var mUserItem: User
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        setUpViewModel()
        setUpUI()
        setupObservers()
    }

    private fun setUpUI() {
        mUserItem = User(0,0,"","","")
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(arrayListOf()) { selectedItem: User -> listItemClicked(selectedItem) }
        binding.recycleView.addItemDecoration(DividerItemDecoration(recycle_view.context,(binding.recycleView.layoutManager as LinearLayoutManager).orientation))
        binding.recycleView.adapter = userAdapter
    }

    private fun setUpViewModel() {
        view = ViewModelProvider(this)[UserViewModel::class.java]
    }

    private fun setupObservers() {
        view.getUsersList().observe(this, Observer {
            it?.let {
                resource ->
                when (resource.status){
                    Status.SUCCESS->{
                    binding.recycleView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let {
                            users-> retrieveList(users)
                        }
                    }
                    Status.ERROR ->{
                        binding.recycleView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING->{
                        binding.recycleView.visibility = View.GONE
                        binding.progressBar.visibility =  View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        userAdapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }


    private fun listItemClicked(item: User) {
        this.mUserItem = item
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("clicked_data", mUserItem)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    override fun onBackPressed()

    {         AlertDialog.Builder(this)
                .setTitle("MVVM Demo")
                .setMessage("Are you sure you want to exit")
                .setPositiveButton(android.R.string.yes) { _: DialogInterface?, _: Int -> finishAffinity() }
                .setNegativeButton(android.R.string.no) { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }
                .setIcon(R.mipmap.ic_launcher)
                .show()
    }
}
