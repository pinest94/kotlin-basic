package com.pinest94.springrediswithkotlin.domain

import com.pinest94.springrediswithkotlin.adapter.MessageAdapter
import com.pinest94.springrediswithkotlin.adapter.TemplateAdapter
import com.pinest94.springrediswithkotlin.util.getCurrentTime
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeout
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class Notification(val message: String)

@Service
class MessageService(
    private var messageAdapter: MessageAdapter,
    private var templateAdapter: TemplateAdapter
) {
    @Transactional
    suspend fun getMessage(mid: String): List<Message> =
        coroutineScope {

            async { withTimeout(5000L) { removeWithExpired(mid) } }
            val deferredMessages = async { withTimeout(5000L) { messageAdapter.get(mid) } }

            val messages = deferredMessages.await()

            val notifications: MutableList<Notification>

            messages
        }

    @Transactional
    suspend fun removeWithExpired(mid: String) = messageAdapter.remove(mid)
}