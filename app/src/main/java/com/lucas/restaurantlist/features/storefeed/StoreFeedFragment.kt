package com.lucas.restaurantlist.features.storefeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lucas.restaurantlist.R
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.features.storedetails.StoreDetailsFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Displays the list of Stores with its title, description and the cover image to the user.
 */
class StoreFeedFragment : Fragment() {
    private lateinit var storeFeedAdapter: StoreFeedAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout

    private val viewModel: StoreFeedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store_feed, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)

        storeFeedAdapter = StoreFeedAdapter { store -> goToDetailsScreen(store) }
        recyclerView = view.findViewById(R.id.stores_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
             adapter = storeFeedAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservers()

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getStoreFeed()
        }
    }

    private fun setupViewModelObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getStoreFeedState.collect { state ->
                    when(state) {
                        is StoreFeedViewModel.StoreFeedState.BindData -> {
                            storeFeedAdapter.submitList(state.storeFeed)
                            swipeRefreshLayout.isRefreshing = false
                        }
                        is StoreFeedViewModel.StoreFeedState.Error -> {
                            swipeRefreshLayout.isRefreshing = false
                            Toast.makeText(
                                requireContext(),
                                "Error while fetching store feed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is StoreFeedViewModel.StoreFeedState.Loading -> {
                            swipeRefreshLayout.isRefreshing = true
                        }
                    }
                }
            }
        }
    }

    private fun goToDetailsScreen(store: StoreResponse) {
        val detailsFragment = StoreDetailsFragment.newInstance(store)
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TAG = "StoreFeedFragment"
    }
}
