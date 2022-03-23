package com.github.harshadnawathe.x.counterkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CounterKtApplication

fun main(args: Array<String>) {
    runApplication<CounterKtApplication>(*args)
}
