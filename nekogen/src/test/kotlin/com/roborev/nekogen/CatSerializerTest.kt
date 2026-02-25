package com.roborev.nekogen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CatSerializerTest {
  @Test
  fun startShouldReturnBeginningOfXml() {
    val catStr = CatSerializer.start()
    assertThat(catStr).isEqualTo("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n<map>\n    <int name=\"food\" value=\"0\" />\n    <float name=\"water\" value=\"0.0\" />\n")
  }
  @Test
  fun serializeShouldSerializeCorrectly() {
    val catStr = CatSerializer.serialize(Cat(5700043918832683565, Body.BLACK_NO_CAP, true, true, true, true, FootSpots.NO_SPOTS, Collar.WHITE, true))
    assertThat(catStr).isEqualTo("    <string name=\"cat:5700043918832683565\">Sir Nightshade Warrior of Darkness</string>\n")
  }
  @Test
  fun endShouldReturnEndOfXml() {
    val catStr = CatSerializer.end()
    assertThat(catStr).isEqualTo("</map>\n")
  }
}