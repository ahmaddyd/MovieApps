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
import com.jetpack.movies.databinding.FragmentTvShowsFavouriteBinding
import com.jetpack.movies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.jetpack.movies.utils.gone
import com.jetpack.movies.utils.visible
import com.jetpack.movies.viewAdapter.FavouriteTvShowsAdapter
import com.jetpack.movies.viewmodel.TvShowsFavouriteViewModel

class FavouriteTvShowsFragment : Fragment() {
    private var _binding: FragmentTvShowsFavouriteBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: TvShowsFavouriteViewModel
    private lateinit var adapter: FavouriteTvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowsFavouriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.recyclerViewTvShowsFavourite)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowsFavouriteViewModel::class.java]

            adapter = FavouriteTvShowsAdapter()

            binding?.progressBar?.visible()

            viewModel.getFavorites().observe(viewLifecycleOwner, {
                binding?.progressBar?.gone()
                adapter.submitList(it)
            })

            binding?.recyclerViewTvShowsFavourite.apply {
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
                val tvEntity = adapter.getSwipedData(swipedPosition)
                tvEntity?.let { viewModel.setFavorite(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.undelete, Snackbar.LENGTH_LONG)

                snackbar.setAction(R.string.message_ok) { _ ->
                    tvEntity?.let { viewModel.setFavorite(it) }
                }

                snackbar.show()
            }
        }
    })
}