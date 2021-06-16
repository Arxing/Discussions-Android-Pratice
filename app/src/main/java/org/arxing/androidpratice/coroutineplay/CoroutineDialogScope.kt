package org.arxing.androidpratice.coroutineplay

import android.app.AlertDialog.Builder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

suspend fun <T> coroutineDialog(scope: suspend CoroutineDialogScope<T>.() -> Unit) {
  scope(CoroutineDialogScope())
}

class CoroutineDialogScope<T> {
  private val lock = AtomicBoolean(true)
  private val result = AtomicReference<T>()

  fun emitValue(value: T) {
    result.set(value)
    lock.set(false)
  }

  fun emitCancel() {
    lock.set(false)
  }

  suspend fun Builder.showAwait(): T {
    withContext(Dispatchers.Main) {
      show()
    }
    withContext(Dispatchers.IO) {
      while (lock.get()) {
        delay(100)
      }
    }
    return result.get()
  }
}