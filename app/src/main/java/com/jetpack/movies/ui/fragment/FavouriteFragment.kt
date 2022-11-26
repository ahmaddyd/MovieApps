package com.jetpack.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jetpack.movies.databinding.FragmentFavouriteBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.jetpack.movies.viewAdapter.PagerAdapter

class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = PagerAdapter(requireContext(), requireActivity())
        binding?.viewPager2?.adapter = pagerAdapter

        val tabLayout = binding?.tabLayout

        val viewPager = binding?.viewPager2

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = pagerAdapter.getPageTitle(position)
            }.attach()
        }
    }
}