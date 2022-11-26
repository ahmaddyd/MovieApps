package com.jetpack.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.movies.databinding.FragmentMoviesBinding
import com.jetpack.movies.utils.gone
import com.jetpack.movies.utils.visible
import com.jetpack.movies.viewAdapter.MoviesAdapter
import com.jetpack.movies.viewmodel.MoviesViewModel
import com.jetpack.movies.viewmodel.ViewModelFactory
import com.jetpack.movies.vo.Status

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            val adapter = MoviesAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> binding?.progressBar?.visible()

                        Status.SUCCESS -> {
                            binding?.progressBar?.gone()
                            adapter.submitList(movie.data)
                        }

                        Status.ERROR -> {
                            binding?.progressBar?.gone()
                            Toast.makeText(context, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            binding?.recyclerViewMovies.apply {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }
}