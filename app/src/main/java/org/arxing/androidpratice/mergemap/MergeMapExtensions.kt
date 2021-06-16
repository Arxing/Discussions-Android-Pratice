package org.arxing.androidpratice.mergemap

fun mergeMap(vararg maps: Map<*, *>): Map<*, *> {
    return maps.flatMap {
        it.entries
    }.fold(mutableMapOf<Any?, Any?>()) { acc, entry ->
        val key = entry.key
        val value = entry.value
        val keyExists = acc.containsKey(key)

        if (keyExists) {
            val originValue = acc[key]
            val newValue: Any = when (value) {
                is Map<*, *> -> {
                    check(originValue is Map<*, *>)
                    mergeMap(originValue, value)
                }
                is List<*> -> {
                    check(originValue is List<*>)
                    concatList(originValue, value)
                }
                else -> error("Key[$key] was already exists.")
            }
            acc[key] = newValue
        } else {
            acc[key] = value
        }
        acc
    }
}

fun concatList(vararg lists: List<*>): List<*> {
    return lists.flatMap { it }
}

fun Map<*, *>.mergeWith(other: Map<*, *>): Map<*, *> = mergeMap(this, other)

fun List<*>.concatWith(other: List<*>): List<*> = concatList(this, other)
