package com.zavaitar.core.network.model

import com.google.gson.annotations.SerializedName

data class LawyerDataResponse(

	@SerializedName("member_since")
	val memberSince: String? = null,

	@SerializedName("image_url")
	val imageUrl: String? = null,

	@SerializedName("price_per_hour")
	val pricePerHour: String? = null,

	@SerializedName("rating")
	val rating: Double? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("firstName")
	val firstName: String? = null,

	@SerializedName("speciality")
	val speciality: String? = null,

	@SerializedName("additional_info")
	val additionalInfo: String? = null,

	@SerializedName("surname")
	val surname: String? = null,

	@SerializedName("ranking")
	val ranking: String? = null,

	@SerializedName("response_time")
	val responseTime: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("no_of_reviews")
	val noOfReviews: Int? = null
)