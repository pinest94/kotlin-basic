package com.pinest94.springrediswithkotlin.domain

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Serializable

@Serializable
data class Template(
    val templateId: Int,
    val cpId: Int,
    val cpName: String,
    val serviceCountry: String,
    val content: String,
    val landingUrl: String,
    val valid: Boolean,
    val activePeriodFrom: String,
    val activePeriodTo: String,
    val activeTimeFrom: String,
    val activeTimeTo: String,
    // @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    val devices: MutableList<String> = mutableListOf(),
    val appVersionFrom: String,
    val appVersionTo: String?,
    val unique: Boolean,
    val count: Int,
    val order: Int,
)