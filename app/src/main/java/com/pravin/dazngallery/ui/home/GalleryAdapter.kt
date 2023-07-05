package com.pravin.dazngallery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pravin.dazngallery.R
import com.pravin.dazngallery.databinding.FragmentItemGalleryBinding
import com.pravin.dazngallery.domain.model.Gallery
import com.squareup.picasso.Picasso


class GalleryAdapter(
    private val values: List<Gallery>,
    private val clickListener: ListItemClickListener<Gallery>
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Picasso.get().load(item.hdurl)
            .resize(150, 150)
            .placeholder(R.drawable.baseline_image_24)
            .error(R.drawable.baseline_broken_image_24)
            .into(holder.ivGallery)

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(position, item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivGallery: ImageView = binding.ivGallery

    }

}