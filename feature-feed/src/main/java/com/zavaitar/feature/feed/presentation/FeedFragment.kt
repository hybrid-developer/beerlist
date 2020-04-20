package com.zavaitar.feature.feed.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zavaitar.core.viewmodel.ViewModelFactory
import com.zavaitar.feature.feed.R
import com.zavaitar.feature.feed.adapter.OnItemSelectedListener
import com.zavaitar.feature.feed.adapter.LawyerFeedRecyclerViewAdapter
import com.zavaitar.feature.feed.model.LawyerFeed
import com.zavaitar.feature.feed.navigation.FeedNavigator
import com.zavaitar.feature.feed.viewmodel.FeedViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.feed_list_fragment.*
import kotlinx.android.synthetic.main.feed_list_fragment.view.*
import javax.inject.Inject

class FeedFragment : Fragment(), OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var feedNavigator: FeedNavigator

    private val viewModel: FeedViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FeedViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedToolbar.let {
            it.title = getString(R.string.app_name)
            it.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.lightBlack))
        }

        setupRetryButton()
        subscribeObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadLawyerFeed()
    }

    override fun onItemSelect(item: LawyerFeed?) {
        feedNavigator.navigateToDetailsScreen(requireView(), item)
    }

    private fun subscribeObservers() {
        viewModel.lawyerFeedEvent.observe(viewLifecycleOwner, renderTwitterFeedItems)
        viewModel.errorScreenEvent.observe(viewLifecycleOwner, renderErrorScreenEvent)
        viewModel.dataLoadingStopEvent.observe(viewLifecycleOwner, renderDataLoadingStopEvent)
        viewModel.errorMessageDisplayStatusEvent.observe(
            viewLifecycleOwner,
            renderErrorMessageDisplayEvent
        )
    }

    private val renderErrorScreenEvent = Observer<Int> { errorMessage ->
        errorMessageTextView.text = getString(errorMessage)
    }

    private val renderDataLoadingStopEvent = Observer<Void> {
        feedLoadingProgressBar.visibility = View.GONE
    }

    private val renderTwitterFeedItems = Observer<List<LawyerFeed>> {
        twitterFeedRecyclerView.adapter = LawyerFeedRecyclerViewAdapter(it, this)
    }

    private val renderErrorMessageDisplayEvent = Observer<Boolean> { visibilityStatus ->
        errorMessageLayout.visibility = if (visibilityStatus) View.VISIBLE else View.GONE
    }

    private fun setupRetryButton() {
        retryNetworkCallButton.setOnClickListener {
            viewModel.loadLawyerFeed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.feed_list_fragment, container, false)
        if (rootView.twitterFeedRecyclerView is RecyclerView) {
            with(rootView.twitterFeedRecyclerView) {
                layoutManager = LinearLayoutManager(context)
            }
        }
        return rootView
    }
}
