package com.roborev.nekogen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CatBuilderTest {
  @Test
  fun createCatShouldMatchAndroidImp() {
    var cat = CatBuilder.createCat(0L, listOf(Collar.WHITE), BowTieFilter.ANY)
    assertThat(cat).isNotNull
    assertThat(cat!!.seed).isEqualTo(0L)
    assertThat(cat.body).isEqualTo(Body.GRAY_BLACK_CAP)
    assertThat(cat.hasBellySpot).isTrue
    assertThat(cat.hasBackSpot).isFalse
    assertThat(cat.hasFaceSpot).isFalse
    assertThat(cat.hasTailSpot).isFalse
    assertThat(cat.footSpots).isEqualTo(FootSpots.NO_SPOTS)
    assertThat(cat.collar).isEqualTo(Collar.WHITE)
    assertThat(cat.isBowTie).isFalse
    assertThat(cat.name).isEqualTo("Dame Ericka Beastmaster of Darkness")
    cat = CatBuilder.createCat(627189298506124733L, listOf(Collar.BLUE), BowTieFilter.BOW_TIE_ONLY)
    assertThat(cat).isNotNull
    assertThat(cat!!.seed).isEqualTo(627189298506124733L)
    assertThat(cat.body).isEqualTo(Body.BLACK_NO_CAP)
    assertThat(cat.hasBellySpot).isFalse
    assertThat(cat.hasBackSpot).isFalse
    assertThat(cat.hasFaceSpot).isFalse
    assertThat(cat.hasTailSpot).isFalse
    assertThat(cat.footSpots).isEqualTo(FootSpots.ALL)
    assertThat(cat.collar).isEqualTo(Collar.BLUE)
    assertThat(cat.isBowTie).isTrue
    assertThat(cat.name).isEqualTo("Sir Nightshade Necromancer of Light")
  }
}