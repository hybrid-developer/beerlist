package com.zavaitar.core.network.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LawyerFeedResponse(
    @SerializedName("member_since") val memberSince: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("price_per_hour") val pricePerHour: String?,
    @SerializedName("rating") val rating: Double,
    @SerializedName("description") val description: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("speciality") val speciality: String?,
    @SerializedName("additional_info") val additionalInfo: String?,
    @SerializedName("surname") val surname: String?,
    @SerializedName("ranking") val ranking: String?,
    @SerializedName("response_time") val responseTime: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("no_of_reviews") val noOfReviews: Int
)