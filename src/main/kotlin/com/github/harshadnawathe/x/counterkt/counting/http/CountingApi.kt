package com.github.harshadnawathe.x.counterkt.counting.http

import com.github.harshadnawathe.x.counterkt.counting.Counter
import com.github.harshadnawathe.x.counterkt.counting.CountingOperations
import com.github.harshadnawathe.x.counterkt.counting.Search
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/counters")
class CountingApi(
    private val countingOps : CountingOperations
) {
    @PostMapping
    fun create(
        @RequestBody request: CounterRequest
    ) : ResponseEntity<Counter> {
        return countingOps.create(request.name, request.count)
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping
    fun list(
        @RequestParam(required = false) name: String? = null,
        @RequestParam(required = false) count: Int? = null,
        pageable: Pageable
    ) : ResponseEntity<List<Counter>> {
        return countingOps.list(Search.by(name, count), pageable)
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : ResponseEntity<Counter> {
        return countingOps.get(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}/name")
    fun updateName(@PathVariable id: String, @RequestBody name: String) : ResponseEntity<Counter> {
        return countingOps.update(id, name)
            .let { ResponseEntity.ok(it) }
    }

    @PutMapping("/{id}/count")
    fun updateCount(@PathVariable id: String, @RequestBody count: Int) : ResponseEntity<Counter>{
        return countingOps.update(id, count)
            .let { ResponseEntity.ok(it) }
    }

    @PostMapping("/{id}/increment")
    fun increment(@PathVariable id: String) :ResponseEntity<Counter> {
        return countingOps.increment(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/{id}/decrement")
    fun decrement(@PathVariable id: String) :ResponseEntity<Counter> {
        return countingOps.decrement(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}

data class CounterRequest(val name: String, val count: Int)