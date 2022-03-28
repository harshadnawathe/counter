package com.github.harshadnawathe.x.counterkt.counting

import io.kotest.matchers.shouldBe
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.random.Random.Default.nextInt

@DisplayName("counter")
class CounterTest {

    @Nested
    @DisplayName("when constructed using primary constructor")
    inner class EntityConstructorTest {

        @Test
        fun `should set name on the object`() {
            val counterName = randomName()

            val counter = Counter(name = counterName, count = randomCount())

            counter.name shouldBe counterName
        }

        @Nested
        @DisplayName("on blank name")
        inner class BlankNameTest {

            @Test
            fun `should throw exception`() {
                assertThatThrownBy {
                    Counter(name = "", count = randomCount())
                }.isInstanceOf(IllegalArgumentException::class.java)
            }

            @Test
            fun `exception should contain correct message`() {
                assertThatThrownBy {
                    Counter(name = "", count = randomCount())
                }.hasMessage("Counter name cannot be blank")
            }
        }

        @Test
        fun `should set count on the object`() {
            val count = randomCount()

            val counter = Counter(name = randomName(), count= count)

            counter.count shouldBe count
        }

        @Nested
        @DisplayName("on negative count")
        inner class NegativeCountTest {
            @Test
            fun `should throw exception`() {
                assertThatThrownBy {
                    Counter(name = randomName(), count = -randomCount())
                }.isInstanceOf(IllegalArgumentException::class.java)
            }

            @Test
            fun `exception should contain correct message`() {
                assertThatThrownBy {
                    Counter(name = randomName(), count = -randomCount())
                }.hasMessage("Counter count cannot be negative")
            }
        }
    }

    @Nested
    @DisplayName("when constructed using persistence constructor")
    inner class PersistenceConstructorTest {
        @Test
        fun `should set id on the object`() {
            val counterId = randomId()

            val counter = Counter(id = counterId, randomName(), randomCount())

            counter.id shouldBe counterId
        }

        @Test
        fun `should set name on the object`() {
            val counterName = randomName()

            val counter = Counter(id= randomId(),  name = counterName, count = randomCount())

            counter.name shouldBe counterName
        }

        @Test
        fun `should set count on the object`() {
            val count = randomCount()

            val counter = Counter(id= randomId(),  name = randomName(), count = count)

            counter.count shouldBe count
        }
    }

    @Nested
    @DisplayName("when using setter for name")
    inner class NameSetterTest {

        @Test
        fun `should set name on the object`() {
            val counter = Counter(name = randomName(), count = randomCount())
            val newName = randomName()

            counter.name = newName

            counter.name shouldBe newName
        }

        @Nested
        @DisplayName("on blank name")
        inner class BlankNameTest {
            private val counter = Counter(name = randomName(), count = randomCount())
            @Test
            fun `should throw exception`() {
                assertThatThrownBy {
                    counter.name = ""
                }.isInstanceOf(IllegalArgumentException::class.java)
            }

            @Test
            fun `exception should contain correct message`() {
                assertThatThrownBy {
                    counter.name = ""
                }.hasMessage("Counter name cannot be blank")
            }
        }

    }

    @Nested
    @DisplayName("when using setter for count")
    inner class CountSetterTest {
        @Test
        fun `should set count on the object`() {
            val counter = Counter(name = randomName(), count = randomCount())
            val newCount = randomCount()

            counter.count = newCount

            counter.count shouldBe newCount
        }

        @Nested
        @DisplayName("on negative count")
        inner class NegativeCountTest {
            private val counter = Counter(name = randomName(), count = randomCount())

            @Test
            fun `should throw exception`() {
                assertThatThrownBy {
                   counter.count = -randomCount()
                }.isInstanceOf(IllegalArgumentException::class.java)
            }

            @Test
            fun `exception should contain correct message`() {
                assertThatThrownBy {
                    counter.count = -randomCount()
                }.hasMessage("Counter count cannot be negative")
            }
        }
    }

    private fun randomId() = randomAlphanumeric(24)
    private fun randomName() = randomAlphabetic(10)
    private fun randomCount() = nextInt(0, 100)

}