package com.zavaitar.feature.feed.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.zavaitar.feature.feed.R

import com.zavaitar.feature.feed.model.LawyerFeed

import kotlinx.android.synthetic.main.feed_list_item.view.*

class LawyerFeedRecyclerViewAdapter(
    private val mValues: List<LawyerFeed>,
    private val mListener: OnItemSelectedListener?
) : RecyclerView.Adapter<LawyerFeedRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private lateinit var context: Context

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as LawyerFeed
            mListener?.onItemSelect(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.nameView.text = "Lawyer's Name"//item.firstName
        holder.descriptionView.text = "Speciality" //item.description
        holder.ratingView.text = "${item.rating}"
        holder.priceView.text = item.pricePerHour

        holder.mainImageView.clipToOutline = true

        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(context)
            .load(item.imageUrl)
            .apply(requestOptions)
            .into(holder.mainImageView)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val nameView: TextView = mView.item_name
        val descriptionView: TextView = mView.item_description
        val mainImageView: ImageView = mView.item_image
        val ratingView: TextView = mView.item_rating
        val priceView: TextView = mView.item_price

        override fun toString(): String {
            return super.toString() + " '" + descriptionView.text + "'"
        }
    }
}