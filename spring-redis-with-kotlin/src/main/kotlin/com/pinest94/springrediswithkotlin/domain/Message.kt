package com.pinest94.springrediswithkotlin.domain

data class Message(
    val mid: String,
    val messageId: String,
    val cp: String,
    val contents: String,
    val landingPage: String?,
    val iconImage: String?,
    val exposureOrder: Int,
    val exposureFlag: Boolean,
    val validationFlag: Boolean,
    val createdAt: String,
    val fedAt: String,
    val expiredAt: String
)