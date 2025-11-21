package com.lucas.restaurantlist.features.storefeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.lucas.restaurantlist.R
import com.lucas.restaurantlist.data.model.StoreResponse

class StoreDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val store = arguments?.getSerializable(ARG_STORE) as? StoreResponse

        store?.let {
            view.findViewById<TextView>(R.id.name).text = it.name
            view.findViewById<TextView>(R.id.description).text = it.description
            view.findViewById<TextView>(R.id.status).text = it.status
            view.findViewById<TextView>(R.id.delivery_fee).text = "Delivery Fee: ${it.deliveryFeeCents}"

            val coverImage = view.findViewById<ImageView>(R.id.cover_image)
            Glide.with(this)
                .load(it.coverImgUrl)
                .into(coverImage)
        }
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
