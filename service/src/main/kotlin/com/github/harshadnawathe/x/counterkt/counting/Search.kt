package com.github.harshadnawathe.x.counterkt.counting

sealed class Search {
    object All : Search()
    data class ByName(val name: String) : Search()
    data class ByCount(val count: Int) : Search()
    data class ByNameAndCount(val name: String, val count: Int) : Search()

    companion object {
        fun by(name: String? = null, count: Int? = null): Search {
            if (name != null && count != null) {
                return ByNameAndCount(name, count)
            }
            if (name != null) {
                return ByName(name)
            }
            if (count != null) {
                return ByCount(count)
            }
            return All
        }
    }
}