package org.arxing.androidpratice.mergemap

import com.google.gson.GsonBuilder

fun main() {
    val map1 = mapOf(
        "name" to "Arxing",
        "age" to 28,
        "location" to mapOf(
            "country" to "Taiwan"
        ),
        "deep-obj-1" to mapOf(
            "a" to 1,
            "deep-obj-2" to mapOf(
                "a-a" to 2,
                "deep-obj-3" to mapOf(
                    "a-a-a" to 3,
                ),
            ),
        ),
    )

    val map2 = mapOf(
        "sex" to "male",
        "location" to mapOf(
            "city" to "Taipei",
        ),
        "deep-obj-1" to mapOf(
            "b" to 10,
            "deep-obj-2" to mapOf(
                "b-b" to 20,
                "deep-obj-3" to mapOf(
                    "b-b-b" to 30,
                ),
            ),
        ),
    )

    val merged = map1.mergeWith(map2)
    println(GsonBuilder().setPrettyPrinting().create().toJson(merged))
    /*
    * Result
    * ----------------------------------
    * {
    *   "name": "Arxing",
    *   "age": 28,
    *   "location": {
    *     "country": "Taiwan",
    *     "city": "Taipei"
    *   },
    *   "deep-obj-1": {
    *     "a": 1,
    *     "deep-obj-2": {
    *       "a-a": 2,
    *       "deep-obj-3": {
    *         "a-a-a": 3,
    *         "b-b-b": 30
    *       },
    *       "b-b": 20
    *     },
    *     "b": 10
    *   },
    *   "sex": "male"
    * }
    * ----------------------------------
    * */
}
