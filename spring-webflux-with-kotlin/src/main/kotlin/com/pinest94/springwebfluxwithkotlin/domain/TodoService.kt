package com.pinest94.springwebfluxwithkotlin.domain

import kotlinx.coroutines.runBlocking

class TodoService {
    fun scoreJoinEventListener(@Payload message: String, ack: Acknowledgment) {
        log.info("[Kafka Consumed] topic: linescore-member-join-event-v1, message: {}", message)
        val scoreJoinInfoKafkaMessage: ScoreJoinInfoKafkaMessage =
            ObjectMapperUtil.readValue(message, ScoreJoinInfoKafkaMessage::class.java)
        if (currentProfile.isDbUpdate) {
            runBlocking {
                processJoinEventMessage(scoreJoinInfoKafkaMessage.toJoinInfoList())
            }
        }
        log.info("[Kafka Consumed] topic: linescore-member-join-event-v1, message: {}", message)
    }
}