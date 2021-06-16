@file:Suppress("UNREACHABLE_CODE", "RedundantSuspendModifier")

package org.arxing.androidpratice.coroutineplay

import android.app.AlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun fetchSomethingFromNetwork(): String = TODO()

fun demo() {
  runBlocking {
    coroutineDialog<Int> {
      val question1Deferred = withContext(Dispatchers.Main) {
        async {
          AlertDialog.Builder(TODO())
            .setNegativeButton("A") { _, _ ->
              emitValue(10)
            }.setPositiveButton("B") { _, _ ->
              emitValue(20)
            }.setNeutralButton("No") { _, _ ->
              emitCancel()
            }
            .showAwait()
        }
      }

      val question2Deferred = withContext(Dispatchers.Main) {
        async {
          AlertDialog.Builder(TODO())
            .setNegativeButton("C") { _, _ ->
              emitValue(30)
            }.setPositiveButton("D") { _, _ ->
              emitValue(40)
            }.setNeutralButton("No") { _, _ ->
              emitCancel()
            }
            .showAwait()
        }
      }

      val somethingDeferred = withContext(Dispatchers.IO) {
        async {
          fetchSomethingFromNetwork()
        }
      }

      val sum = question1Deferred.await() + question2Deferred.await()
      somethingDeferred.await()
    }
  }
}