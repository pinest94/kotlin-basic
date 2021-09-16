package com.pinest94.springrediswithkotlin.util

import java.time.LocalDateTime
import java.time.ZoneOffset

fun getCurrentTime() = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)