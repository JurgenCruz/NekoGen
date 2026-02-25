package com.roborev.nekogen

import kotlinx.coroutines.channels.Channel
import java.nio.file.Path
import kotlin.io.path.bufferedWriter

@Suppress("HardCodedStringLiteral")
object CatWriter {
  suspend fun write(path: Path, catChannel: Channel<Cat>, start: () -> String, serialize: (cat: Cat) -> String, end: () -> String) {
    path.bufferedWriter().use { writer ->
      writer.write(start())
      var i = 0
      for (cat in catChannel) {
        println("writing cat #${++i}")
        writer.write(serialize(cat))
      }
      writer.write(end())
    }
  }
}