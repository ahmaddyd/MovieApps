package com.jetpack.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.movies.databinding.FragmentTvShowsBinding
import com.jetpack.movies.utils.gone
import com.jetpack.movies.utils.visible
import com.jetpack.movies.viewAdapter.TvShowsAdapter
import com.jetpack.movies.viewmodel.TvShowsViewModel
import com.jetpack.movies.viewmodel.ViewModelFactory
import com.jetpack.movies.vo.Status

class TvShowsFragment : Fragment() {
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]

            val adapter = TvShowsAdapter()
            viewModel.getTvs().observe(viewLifecycleOwner, { tv ->
                if (tv != null) {
                    when (tv.status) {
                        Status.LOADING -> binding?.progressBar?.visible()

                        Status.SUCCESS -> {
                            binding?.progressBar?.gone()
                            adapter.submitList(tv.data)
                        }

                        Status.ERROR -> {
                            binding?.progressBar?.gone()
                            Toast.makeText(context, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            binding?.recyclerViewTvShows.apply {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }
}