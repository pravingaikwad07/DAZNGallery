package com.pravin.dazngallery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.pravin.dazngallery.data.DashboardViewModel
import com.pravin.dazngallery.databinding.FragmentDashboardBinding
import com.pravin.dazngallery.domain.model.Gallery
import com.pravin.dazngallery.utils.GRID_ITEM_COUNT
import com.pravin.dazngallery.utils.NASA_DETAILS_JSON
import com.pravin.dazngallery.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.loadImages(NASA_DETAILS_JSON)

        CoroutineScope(Dispatchers.Main).launch {
            dashboardViewModel.imageListState.collect { data ->
                when {
                    data.isLoading -> {
                        binding.progressBar.isVisible = true
                    }

                    data.imageList.isNotEmpty() -> {
                        binding.progressBar.isVisible = false
                        setRecyclerviewAdapter(data.imageList)
                    }

                    data.error.isNotBlank() -> {
                        binding.progressBar.isVisible = false
                        requireContext().showToast(data.error)
                    }
                }
            }
        }


        return root
    }

    private fun setRecyclerviewAdapter(imageList: List<Gallery>) {
        layoutManager = GridLayoutManager(requireContext(), GRID_ITEM_COUNT)
        binding.rvGallery.layoutManager = layoutManager
        val galleryAdapter = GalleryAdapter(imageList, object : ListItemClickListener<Gallery> {
            override fun onItemClick(position: Int, item: Gallery) {


                val actionList  =
                    HomeFragmentDirections.actionNavigationImageGridToNavigationImageDetails(
                        imageList.toTypedArray(),
                        position
                    )

                findNavController().navigate(actionList)
            }
        })

        binding.rvGallery.adapter = galleryAdapter
        binding.rvGallery.addItemDecoration(
            DividerItemDecoration(
                requireContext(), (GridLayoutManager(requireContext(), 1)).orientation
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}