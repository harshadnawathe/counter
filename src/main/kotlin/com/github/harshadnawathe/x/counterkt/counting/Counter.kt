package com.github.harshadnawathe.x.counterkt.counting

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "counters")
class Counter(name: String, count: Int) {
    var name: String = check(name)
        set(value) {
            field = check(value)
        }

    var count : Int = check(count)
        set(value) {
            field = check(value)
        }

    @Id
    lateinit var id : String
        private set

    @PersistenceConstructor
    constructor(id: String, name: String, count: Int) : this(name, count) {
        this.id = id
    }

    companion object {
        private fun check(name: String) = requireNotNull(name.takeIf { it.isNotBlank() }) {
            "Counter name cannot be blank"
        }

        private fun check(count: Int) = requireNotNull(count.takeIf { it >= 0 }) {
            "Counter count cannot be zero"
        }
    }
}