package com.pravin.dazngallery.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pravin.dazngallery.databinding.FragmentItemImageDetailsBinding
import com.pravin.dazngallery.domain.model.Gallery
import com.squareup.picasso.Picasso


class GalleryViewPagerAdapter(
    private val values: List<Gallery>,
) : RecyclerView.Adapter<GalleryViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemImageDetailsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        Picasso.get().load(item.url).into(holder.imageView)
        holder.tvDate.text = item.date
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.explanation
        holder.tvCopyright.text = item.copyright

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemImageDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvCopyright: TextView = binding.tvCopyright
        val tvDate: TextView = binding.tvDate
        val tvDescription: TextView = binding.tvDescription
        val tvTitle: TextView = binding.tvTitle
        val imageView: ImageView = binding.imageView

    }

}