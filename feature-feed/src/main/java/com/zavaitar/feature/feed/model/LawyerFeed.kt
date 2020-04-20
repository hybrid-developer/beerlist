package com.zavaitar.feature.feed.model

import android.os.Parcelable
import com.zavaitar.core.network.model.LawyerFeedResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LawyerFeed(
    val id: Int,
    val memberSince: String? = null,
    val imageUrl: String? = null,
    val pricePerHour: String? = null,
    val rating: Double,
    val description: String? = null,
    val firstName: String? = null,
    val speciality: String? = null,
    val additionalInfo: String? = null,
    val surname: String? = null,
    val ranking: String? = null,
    val responseTime: String? = null,
    val noOfReviews: Int
): Parcelable

fun LawyerFeedResponse.convertToFeed(): LawyerFeed {
    return LawyerFeed(
        id,
        memberSince,
        imageUrl,
        pricePerHour,
        rating,
        description,
        firstName,
        speciality,
        additionalInfo,
        surname,
        ranking,
        responseTime,
        noOfReviews
    )
}
