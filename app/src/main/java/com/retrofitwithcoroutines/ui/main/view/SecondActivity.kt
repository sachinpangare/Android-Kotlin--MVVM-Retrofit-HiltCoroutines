package com.retrofitwithcoroutines.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.retrofitwithcoroutines.R
import com.retrofitwithcoroutines.data.model.User
import com.retrofitwithcoroutines.databinding.SecondActivityBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_data.view.*
import java.lang.Exception

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: SecondActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SecondActivity, R.layout.second_activity)
        setSupportActionBar(binding.toolbar)
        val bundle: Bundle? = intent.extras

        bundle.apply {
            val mUser = this!!.getSerializable("clicked_data") as User?
            if (mUser != null) {
                binding.toolbar.title = mUser.title
                binding.toolbar.subtitle = mUser.id.toString()

                Picasso.get()
                    .load(mUser.url)
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .fit()
                    .into(binding.imageAvatar)
            }
        }


        binding.toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

    }
}
