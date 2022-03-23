package com.github.harshadnawathe.x.counterkt.counting.service

import com.github.harshadnawathe.x.counterkt.counting.*
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class CountingService(
    private val counters: Counters
) : CountingOperations {

    override fun create(name: String, count: Int): Counter {
        return counters.save(Counter(name, count))
    }

    override fun get(id: String): Counter? {
        return counters.findById(id).orElse(null)
    }

    override fun list(search: Search, pageable: Pageable): List<Counter> {
        val result = when(search) {
            is Search.ByCount -> counters.findAllByCount(search.count, pageable)
            is Search.ByName -> counters.findAllByName(search.name, pageable)
            is Search.ByNameAndCount -> counters.findAllByNameAndCount(search.name, search.count, pageable)
            else -> counters.findAll(pageable)
        }

        return result.content
    }

    override fun update(id: String, count: Int): Counter? {
        return counters.findById(id).orElse(null)
            ?.apply {
                this.count = count
            }?.let {
                counters.save(it)
            }
    }

    override fun update(id: String, name: String): Counter? {
        return counters.findById(id).orElse(null)
            ?.apply {
                this.name = name
            }?.let {
                counters.save(it)
            }
    }

    override fun increment(id: String): Counter? {
        return this.get(id)
            ?.let {
                this.update(id, it.count + 1)
            }
    }

    override fun decrement(id: String): Counter? {
        return this.get(id)
            ?.let {
                this.update(id, it.count - 1)
            }
    }
}