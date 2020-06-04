package com.example.belkaapp.ui.events

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.belkaapp.network.EventProperty
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.belkaapp.databinding.GridViewItemBinding

class PhotoGridAdapter(private val onClickListener: OnClickListener): ListAdapter<EventProperty, PhotoGridAdapter.EventPropertyViewHolder>(DiffCallback){

    class EventPropertyViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventProperty: EventProperty) {
            binding.property = eventProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<EventProperty>() {
        override fun areItemsTheSame(oldItem: EventProperty, newItem: EventProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EventProperty, newItem: EventProperty): Boolean {
            return oldItem.event_id == newItem.event_id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.EventPropertyViewHolder {
        return EventPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.EventPropertyViewHolder, position: Int) {
        val eventProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(eventProperty)
        }
        holder.bind(eventProperty)
    }

    class OnClickListener(val clickListener: (eventProperty: EventProperty) -> Unit) {
        fun onClick(eventProperty: EventProperty) = clickListener(eventProperty)
    }
}