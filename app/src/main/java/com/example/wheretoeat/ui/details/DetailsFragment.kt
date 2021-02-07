package com.example.wheretoeat.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val restaurant = args.restaurant

            Glide.with(this@DetailsFragment)
                .load(restaurant.image_url)
                .error(R.drawable.ic_baseline_error_24)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewRestaurantName.isVisible = true
                        textViewCity.isVisible = true
                        textViewAddress.isVisible = true
                        textViewArea.isVisible=true
                        textViewState.isVisible=true
                        textViewPhone.isVisible = true
                        textViewPostalCode.isVisible = true
                        textViewCoordinates.isVisible = true
                        textViewMobileWebpage.isVisible = true
                        textViewWebpage.isVisible = true
                        textViewPrice.isVisible=true
                        return false
                    }
                })
                .into(imageView)

            val rawPhoneNum = restaurant.phone
            val phoneNum = rawPhoneNum.filter { it.isDigit() }

            textViewPrice.apply {
                text = "Price: ${restaurant.price} $$$"
            }
            textViewRestaurantName.apply {
                text = "Restaurant: ${restaurant.name}"
            }
            textViewCity.apply {
                text = "City: ${restaurant.city}"
            }
            textViewState.apply {
                text="State ${restaurant.state}"
            }
            textViewAddress.apply {
                text = "Address: ${restaurant.address}"
            }
            textViewPhone.apply {
                text = "Phone: ${phoneNum}"
            }
            textViewArea.apply {
                text = "Area: ${restaurant.area}"
            }
            textViewPostalCode.apply {
                text = "Postal code:  ${restaurant.postal_code}"
            }
            textViewCoordinates.apply {
                text = "Coordinates: ( ${restaurant.lat} , ${restaurant.lng} )"
            }
            textViewWebpage.apply {
                text = "Webpage: ${restaurant.reserve_url}"
            }
            textViewMobileWebpage.apply {
                text = "Mobile webpage: ${restaurant.mobile_reserve_url}"
            }

            val navUri: Uri =
                Uri.parse("google.navigation:q=${restaurant.lat},${restaurant.lng}")
            val mapIntent = Intent(Intent.ACTION_VIEW, navUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            imageViewDirection.apply {
                setOnClickListener {
                    context.startActivity(mapIntent)
                }
            }

            val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            imageViewPhone.apply {
                setOnClickListener {
                    context.startActivity(phoneIntent)
                }
            }
        }
    }
}

