package com.zavaitar.feature.feed

enum class ErrorType(val errorMessage: Int) {
    EMPTY_DATA(R.string.feed_data_not_received),
    NETWORK_FAILED(R.string.feed_network_error_message),
    OTHER(R.string.feed_other_error)
}