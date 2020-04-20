package com.zavaitar.feature.feed.presentation

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.zavaitar.core.ui.util.toMap
import com.zavaitar.core.viewmodel.ViewModelFactory
import com.zavaitar.feature.feed.R
import com.zavaitar.feature.feed.model.LawyerFeed
import com.zavaitar.feature.feed.viewmodel.FeedDetailsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.feed_details_fragment.*
import javax.inject.Inject

class FeedDetailsFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FeedDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FeedDetailsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feed_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initToolbar()
        viewModel.initSelectedLawyerData(arguments!!.toMap())
    }

    private fun initToolbar() {
        toolbar.let {
            val appCompatActivity = requireActivity() as AppCompatActivity
            appCompatActivity.setSupportActionBar(it)
            it.setNavigationOnClickListener { appCompatActivity.onBackPressed() }
            NavigationUI.setupActionBarWithNavController(appCompatActivity, findNavController())
            it.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.feed_back_button)
            it.title = getString(R.string.feed_layer_details_title)
            it.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.lightBlack))
        }
    }

    private fun subscribeObservers() {
        viewModel.detailsDisplayEvent.observe(viewLifecycleOwner, renderDetailsToTheScreenEvent)
    }

    private val renderDetailsToTheScreenEvent = Observer<LawyerFeed> { lawyerDetails ->
        item_name.text = lawyerDetails.firstName
        item_surname.text = lawyerDetails.surname
        item_description.text = lawyerDetails.speciality
        item_rating.text = "${lawyerDetails.rating}"
        item_price.text = lawyerDetails.pricePerHour

        middle_rating_text.text = "${lawyerDetails.rating}"
        middle_review_text.text = "${lawyerDetails.noOfReviews}"
        middle_ranking_text.text = lawyerDetails.ranking

        member_scince_view.text = getString(R.string.feed_member_since_text, lawyerDetails.memberSince)
        avgresponse_view.text = getString(R.string.feed_response_time_text, lawyerDetails.responseTime)

        description_text.text = lawyerDetails.description
        additional_info_text.text = lawyerDetails.additionalInfo

        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(context)
            .load(lawyerDetails.imageUrl)
            .apply(requestOptions)
            .into(item_image)
    }
}