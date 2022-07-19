package com.github.harshadnawathe.x.counterkt.counting

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.TestPropertySource

@TestPropertySource(
    properties = [
        "spring.mongodb.embedded.version=4.0.4"
    ]
)
@DataMongoTest
class CountersTest {

    @Autowired
    lateinit var repository: Counters

    @Test
    fun `should work`() {
        repository.save(Counter("FooBar", 10))
    }
}