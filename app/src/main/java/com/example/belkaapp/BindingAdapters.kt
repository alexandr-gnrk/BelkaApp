package com.example.belkaapp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.belkaapp.network.EventProperty
import com.example.belkaapp.ui.events.PhotoGridAdapter

//import com.example.meetplanner.network.OverviewMeetingProperty
//import com.example.meetplanner.overview.OverviewAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<EventProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}
@BindingAdapter("imgageUrl")
fun bindRecyclerView(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}