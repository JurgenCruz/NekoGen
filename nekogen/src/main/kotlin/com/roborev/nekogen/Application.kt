package com.roborev.nekogen

import com.roborev.nekogen.CatCalculator.calculate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withContext
import java.nio.file.Paths

suspend fun main(args: Array<String>) {
  withContext(Dispatchers.Default) {
    val catsChannel = Channel<Cat>()
    calculate(catsChannel, 1000, listOf(Collar.BLUE), BowTieFilter.BOW_TIE_ONLY, CatBuilder::createCat)
    CatWriter.write(Paths.get("./mPrefs.xml"), catsChannel, CatSerializer::start, CatSerializer::serialize, CatSerializer::end)
  }
}
