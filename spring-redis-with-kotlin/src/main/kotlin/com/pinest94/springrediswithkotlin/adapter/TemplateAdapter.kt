package com.pinest94.springrediswithkotlin.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.pinest94.springrediswithkotlin.domain.Template
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Component

@Component
class TemplateAdapter(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, Template>,
    private val objectMapper: ObjectMapper
) {
    private val key: String = "cms:templates"
    private val collectionKey: String = "devices"

    private fun key(templateId: String): String {
        return "$key:$templateId"
    }

    suspend fun get(templateId: String): Template {
        // TODO: entry -> 한번에 map형태로 변환방법 있는지
        val result = reactiveRedisTemplate.opsForHash<String, String>().entries(key(templateId)).asFlow().toList()
        var map = mutableMapOf<String, Any?>()
        for((k, v) in result) {
            // TODO: 이렇게 if문을 꼭 써야하는지
            if(k == collectionKey) {
                val list: List<String> = objectMapper.readValue(v, TypeFactory.defaultInstance().constructCollectionType(List::class.java, String::class.java))
                map[k] = list
                continue
            }
            map[k] = v
        }

        return objectMapper.convertValue(map, Template::class.java)
    }
}