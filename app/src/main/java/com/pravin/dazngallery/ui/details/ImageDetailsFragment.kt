package com.pravin.dazngallery.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pravin.dazngallery.databinding.FragmentDetailsBinding

class ImageDetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: ImageDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val images = args.images.toList()
        val currentPosition = args.currentPosition


        val sectionsPagerAdapter = GalleryViewPagerAdapter(images)
        binding.viewPager.adapter = sectionsPagerAdapter

        binding.viewPager.currentItem = currentPosition


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}