package com.lucas.restaurantlist.features.storedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.lucas.restaurantlist.R
import com.lucas.restaurantlist.data.model.StoreDetailsResponse
import com.lucas.restaurantlist.data.model.StoreResponse
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreDetailsFragment : Fragment() {

    private val viewModel: StoreDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservers()

        val store = arguments?.getSerializable(ARG_STORE) as? StoreResponse

        store?.let {
            viewModel.getStoreDetails(it.id, it)
        }
    }

    private fun setupViewModelObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getStoreDetailsState.collect { state ->
                    when (state) {
                        is StoreDetailsViewModel.StoreDetailsState.BindData -> {
                            buildStoreDetails(state.storeDetails)
                        }
                        is StoreDetailsViewModel.StoreDetailsState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Error while fetching store details. Falling back to original.",
                                Toast.LENGTH_SHORT
                            ).show()

                            buildStoreDetails(state.storeDetails)
                        }
                        is StoreDetailsViewModel.StoreDetailsState.Loading -> {
                            view?.findViewById<ProgressBar>(R.id.progress_bar)?.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun buildStoreDetails(storeDetails: StoreDetailsResponse) {
        view?.findViewById<ProgressBar>(R.id.progress_bar)?.visibility = View.GONE
        val status = if (storeDetails.status == "Opened" || storeDetails.status == "Closed") storeDetails.status else "Opens in: ${storeDetails.status}"

        view?.findViewById<TextView>(R.id.name)?.text = storeDetails.name
        view?.findViewById<TextView>(R.id.description)?.text = storeDetails.description
        view?.findViewById<TextView>(R.id.status)?.text = status
        view?.findViewById<TextView>(R.id.delivery_fee)?.text = "Delivery Fee: ${storeDetails.deliveryFeeCents}"

        val coverImage = view?.findViewById<ImageView>(R.id.cover_image)
        Glide.with(this)
            .load(storeDetails.coverImgUrl)
            .into(coverImage as ImageView)
    }

    companion object {
        const val ARG_STORE = "arg_store"

        fun newInstance(store: StoreResponse): StoreDetailsFragment {
            val fragment = StoreDetailsFragment()
            val args = Bundle()
            args.putSerializable(ARG_STORE, store)
            fragment.arguments = args
            return fragment
        }
    }
}