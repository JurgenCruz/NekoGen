package com.roborev.nekogen

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Suppress("HardCodedStringLiteral")
object CatCalculator {
  fun CoroutineScope.calculate(channel: Channel<Cat>, numChunks: Int, collarWhiteList: List<Collar>, bowTieFilter: BowTieFilter, createCat: (Long, List<Collar>, BowTieFilter) -> Cat?) {
    val target = calculateTarget()
    println("Target count: $target")
    val cats = ConcurrentHashMap.newKeySet<Cat>(target)
    val chunkSize = Long.MAX_VALUE / numChunks
    val refCount = AtomicInteger(numChunks)
    (0 until numChunks).forEach {
      val start = it * chunkSize
      val end = start + chunkSize
      launch(Dispatchers.IO) {
        var i = start
        while (cats.size < target && i < end) {
          val cat: Cat? = createCat(i, collarWhiteList, bowTieFilter)
          if (cat != null) {
            if (cats.add(cat)) {
              println("Cat found. Id: ${cat.seed} Name: ${cat.name} from coroutine $it")
              channel.send(cat)
            }
          }
          i++
        }
        val remainingJobs = refCount.decrementAndGet()
        if (remainingJobs == 0) {
          channel.close()
        }
      }
    }
  }
  /**
   * There are 32 body-cap combinations. But all white combinations are special because belly, back, face, feet & tail spots are invisible.
   * There are thus only 3 white body combinations.
   * There are 2 belly, 2 back, 2 face, 8 feet & 2 tail options.
   * Back however does not seem to be working so skipping.
   */
  private fun calculateTarget() = ((Body.entries.size - 3) * 2 * 2 * 8 * 2) + 3
}
