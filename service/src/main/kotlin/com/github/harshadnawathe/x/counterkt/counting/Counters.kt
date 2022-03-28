package com.github.harshadnawathe.x.counterkt.counting

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface Counters : MongoRepository<Counter, String> {
    fun findAllByName(name: String, pageable: Pageable) : Page<Counter>
    fun findAllByCount(count: Int, pageable: Pageable) : Page<Counter>
    fun findAllByNameAndCount(name: String, count: Int, pageable: Pageable) : Page<Counter>
}