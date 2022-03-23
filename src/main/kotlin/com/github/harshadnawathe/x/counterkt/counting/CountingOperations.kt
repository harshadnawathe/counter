package com.github.harshadnawathe.x.counterkt.counting

import org.springframework.data.domain.Pageable

interface CountingOperations {
    fun create(name: String, count: Int = 0) : Counter
    fun get(id : String): Counter?
    fun list(search: Search = Search.All, pageable: Pageable) : List<Counter>
    fun update(id: String, count: Int) : Counter?
    fun update(id: String, name: String) : Counter?
    fun increment(id: String) : Counter?
    fun decrement(id: String) : Counter?
}

