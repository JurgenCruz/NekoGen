package com.roborev.nekogen

import java.util.Random

object CatBuilder {
  private val P_BODY_COLORS: IntArray = intArrayOf(180, 180, 140, 140, 100, 100, 100, 5, 5, 5, 4, 1)
  private val P_COLLAR_COLORS: IntArray = intArrayOf(250, 250, 250, 50, 50, 50, 50, 50)
  private val P_BELLY_COLORS: IntArray = intArrayOf(750, 250)
  private val P_DARK_SPOT_COLORS: IntArray = intArrayOf(700, 250, 50)
  private val P_LIGHT_SPOT_COLORS: IntArray = intArrayOf(700, 300)
  fun createCat(seed: Long, collarWhiteList: List<Collar>, bowTieFilter: BowTieFilter): Cat? {
    val nsr = notSoRandom(seed)
    val bodyIndex = chooseP(nsr, P_BODY_COLORS)
    if (bodyIndex == BodyIndexes.COMPUTED) {
      return null
    }
    val bellyIndex = chooseP(nsr, P_BELLY_COLORS)
    val hasBellySpot = if (bodyIndex != BodyIndexes.WHITE) intToBoolean(bellyIndex) else false
    val backIndex = chooseP(nsr, P_BELLY_COLORS)
    val hasBackSpot = if (bodyIndex != BodyIndexes.WHITE) intToBoolean(backIndex) else false
    if (hasBackSpot) {
      // back spot does not seem to be working :(
      return null
    }
    val faceIndex = chooseP(nsr, P_BELLY_COLORS)
    val hasFaceSpot = if (bodyIndex != BodyIndexes.WHITE) intToBoolean(faceIndex) else false
    var footSpots = when {
      nsr.nextFloat() < 0.25f -> FootSpots.ALL
      nsr.nextFloat() < 0.25f -> FootSpots.FRONT_HALF
      nsr.nextFloat() < 0.25f -> FootSpots.BACK_HALF
      nsr.nextFloat() < 0.1f  -> choose(nsr, FootSpots.FIRST, FootSpots.SECOND, FootSpots.THIRD, FootSpots.FOURTH)
      else                    -> FootSpots.NO_SPOTS
    }
    if (bodyIndex == BodyIndexes.WHITE) {
      footSpots = FootSpots.NO_SPOTS
    }
    val hasTailSpot = nsr.nextFloat() < 0.333f && bodyIndex != BodyIndexes.WHITE
    val body = if (bodyIndex == BodyIndexes.BLACK) {
      val capIndex = chooseP(nsr, P_LIGHT_SPOT_COLORS)
      when (capIndex) {
        CapIndexes.NO_CAP -> Body.BLACK_NO_CAP
        CapIndexes.WHITE  -> Body.BLACK_WHITE_CAP
        else              -> throw IndexOutOfBoundsException()
      }
    } else {
      val capIndex = chooseP(nsr, P_DARK_SPOT_COLORS)
      when (bodyIndex) {
        BodyIndexes.WHITE  -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.WHITE_NO_CAP
          CapIndexes.BLACK  -> Body.WHITE_BLACK_CAP
          CapIndexes.BROWN  -> Body.WHITE_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.GRAY   -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.GRAY_NO_CAP
          CapIndexes.BLACK  -> Body.GRAY_BLACK_CAP
          CapIndexes.BROWN  -> Body.GRAY_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.BROWN  -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.BROWN_NO_CAP
          CapIndexes.BLACK  -> Body.BROWN_BLACK_CAP
          CapIndexes.BROWN  -> Body.BROWN_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.ORANGE -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.ORANGE_NO_CAP
          CapIndexes.BLACK  -> Body.ORANGE_BLACK_CAP
          CapIndexes.BROWN  -> Body.ORANGE_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.BUFF   -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.BUFF_NO_CAP
          CapIndexes.BLACK  -> Body.BUFF_BLACK_CAP
          CapIndexes.BROWN  -> Body.BUFF_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.GREEN  -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.GREEN_NO_CAP
          CapIndexes.BLACK  -> Body.GREEN_BLACK_CAP
          CapIndexes.BROWN  -> Body.GREEN_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.BLUE   -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.BLUE_NO_CAP
          CapIndexes.BLACK  -> Body.BLUE_BLACK_CAP
          CapIndexes.BROWN  -> Body.BLUE_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.STEEL  -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.STEEL_NO_CAP
          CapIndexes.BLACK  -> Body.STEEL_BLACK_CAP
          CapIndexes.BROWN  -> Body.STEEL_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.PURPLE -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.PURPLE_NO_CAP
          CapIndexes.BLACK  -> Body.PURPLE_BLACK_CAP
          CapIndexes.BROWN  -> Body.PURPLE_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        BodyIndexes.PINK   -> when (capIndex) {
          CapIndexes.NO_CAP -> Body.PINK_NO_CAP
          CapIndexes.BLACK  -> Body.PINK_BLACK_CAP
          CapIndexes.BROWN  -> Body.PINK_BROWN_CAP
          else              -> throw IndexOutOfBoundsException()
        }

        else               -> throw IndexOutOfBoundsException()
      }
    }
    val collarIndex = chooseP(nsr, P_COLLAR_COLORS)
    val collar = intToEnum<Collar>(collarIndex)
    if (!collarWhiteList.contains(collar)) {
      return null
    }
    val isBowTie = nsr.nextFloat() < 0.1f
    if (bowTieFilter == BowTieFilter.COLLAR_ONLY && isBowTie || bowTieFilter == BowTieFilter.BOW_TIE_ONLY && !isBowTie) {
      return null
    }
    return Cat(seed, body, hasBellySpot, hasBackSpot, hasFaceSpot, hasTailSpot, footSpots, collar, isBowTie)
  }

  private fun notSoRandom(seed: Long) = Random().also { it.setSeed(seed) }
  private fun chooseP(r: Random, a: IntArray): Int {
    var pct = r.nextInt(1000)
    val stop = a.size - 1
    var i = 0
    while (i < stop) {
      pct -= a[i]
      if (pct < 0) {
        break
      }
      i++
    }
    return i
  }

  private inline fun <reified T> choose(r: Random, vararg l: T) = l[r.nextInt(l.size)]
  private inline fun <reified T : Enum<T>> intToEnum(index: Int) = enumValues<T>().getOrNull(index) ?: throw IndexOutOfBoundsException()
  private fun intToBoolean(index: Int) = when (index) {
    0    -> false
    1    -> true
    else -> throw IndexOutOfBoundsException()
  }
}