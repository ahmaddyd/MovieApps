package com.jetpack.movies.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.movies.R
import com.jetpack.movies.databinding.FragmentMoviesFavouriteBinding
import com.jetpack.movies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.jetpack.movies.utils.gone
import com.jetpack.movies.utils.visible
import com.jetpack.movies.viewAdapter.FavouriteMoviesAdapter
import com.jetpack.movies.viewmodel.MoviesFavouriteViewModel

class FavouriteMoviesFragment : Fragment() {
    private var _binding: FragmentMoviesFavouriteBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: MoviesFavouriteViewModel
    private lateinit var adapter: FavouriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesFavouriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.recyclerViewMoviesFavourite)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesFavouriteViewModel::class.java]

            adapter = FavouriteMoviesAdapter()

            binding?.progressBar?.visible()

            viewModel.getFavorites().observe(viewLifecycleOwner, {
                binding?.progressBar?.gone()
                adapter.submitList(it)
            })

            binding?.recyclerViewMoviesFavourite.apply {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.absoluteAdapterPosition

                val movieEntity = adapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavorite(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.undelete, Snackbar.LENGTH_LONG)

                snackbar.setAction(R.string.message_ok) { _ ->
                    movieEntity?.let { viewModel.setFavorite(it) }
                }

                snackbar.show()
            }
        }
    })
}