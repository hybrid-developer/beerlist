package com.zavaitar.feature.feed.adapter

import com.zavaitar.feature.feed.model.LawyerFeed

interface OnItemSelectedListener {
    fun onItemSelect(item: LawyerFeed?)
}