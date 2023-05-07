package com.retrofitwithcoroutines.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.retrofitwithcoroutines.R
import com.retrofitwithcoroutines.data.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_data.view.*
import com.squareup.picasso.Callback
import java.lang.Exception


class UserAdapter(private val user: ArrayList<User>, private val clickListener: (User) -> Unit): RecyclerView.Adapter<UserAdapter.DataViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
      holder.bind(user[position],clickListener)
    }

    override fun getItemCount(): Int = user.size

    fun addUsers(users: List<User>) {
        this.user.apply {
            clear()
            addAll(users)
        }
    }

    class DataViewHolder(dataView: View) : RecyclerView.ViewHolder(dataView) {
        fun bind(user: User,clickListener: (User) -> Unit) {
            itemView.apply {
                textViewUserName.text = user.id.toString()
                textViewUserEmail.text = user.title

                Picasso.get()
                    .load(user.thumbnailUrl) // small quality url goes here
                    .into(imageViewAvatar, object : Callback {
                        override fun onSuccess() {
                            Picasso.get()
                                .load(user.url) // full quality url goes here
                                .centerCrop()
                                .fit()
                               // .placeholder(R.mipmap.ic_launcher)
                                .into(imageViewAvatar)
                        }

                        override fun onError(e: Exception?) {
                            TODO("Not yet implemented")
                        }
                    })

                itemView.setOnClickListener {
                    clickListener(user)
                }

            }
        }
    }
}