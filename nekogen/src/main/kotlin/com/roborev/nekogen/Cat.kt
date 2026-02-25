package com.roborev.nekogen

/**
 * @param seed The RNG seed used to create this cat.
 * @param body The color of the cat including the cap.
 * @param hasBellySpot True if cat has white spot in the belly, false otherwise.
 * @param hasBackSpot True if cat has white spot in the back, false otherwise.
 * @param hasFaceSpot True if cat has white spot in the face, false otherwise.
 * @param hasTailSpot True if cat has white spot in the tail, false otherwise.
 * @param footSpots Which feet have white spots.
 * @param collar The color of the collar.
 * @param isBowTie True if the collar is a bowtie, false otherwise.
 */
@Suppress("HardCodedStringLiteral")
data class Cat(val seed: Long, val body: Body, val hasBellySpot: Boolean, val hasBackSpot: Boolean, val hasFaceSpot: Boolean, val hasTailSpot: Boolean, val footSpots: FootSpots, val collar: Collar, val isBowTie: Boolean) {
  val name: String
    get() = "${bodyToTitle()} ${bodyToName()} ${toClass()} ${feetToSuffix()}"

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as Cat
    if (body != other.body) return false
    if (hasBellySpot != other.hasBellySpot) return false
    if (hasFaceSpot != other.hasFaceSpot) return false
    if (hasTailSpot != other.hasTailSpot) return false
    if (footSpots != other.footSpots) return false
    return true
  }

  override fun hashCode(): Int {
    var result = body.hashCode()
    result = 31 * result + hasBellySpot.hashCode()
    result = 31 * result + hasFaceSpot.hashCode()
    result = 31 * result + hasTailSpot.hashCode()
    result = 31 * result + footSpots.hashCode()
    return result
  }

  private fun toClass(): String = when {
    hasBellySpot && hasFaceSpot && hasTailSpot   -> "Warrior"
    hasBellySpot && hasFaceSpot && !hasTailSpot  -> "Hunter"
    hasBellySpot && !hasFaceSpot && hasTailSpot  -> "Monk"
    hasBellySpot && !hasFaceSpot && !hasTailSpot -> "Beastmaster"
    !hasBellySpot && hasFaceSpot && hasTailSpot  -> "Mage"
    !hasBellySpot && hasFaceSpot && !hasTailSpot -> "Archer"
    hasTailSpot                                  -> "Bard"
    else                                         -> "Necromancer"
  }

  private fun bodyToName(): String = when (body) {
    Body.BLACK_NO_CAP     -> "Nightshade"
    Body.BLACK_WHITE_CAP  -> "Nightshade"
    Body.WHITE_NO_CAP     -> "Falcor"
    Body.WHITE_BLACK_CAP  -> "Falcoria"
    Body.WHITE_BROWN_CAP  -> "Falcor"
    Body.GRAY_NO_CAP      -> "Erick"
    Body.GRAY_BLACK_CAP   -> "Ericka"
    Body.GRAY_BROWN_CAP   -> "Erick"
    Body.BROWN_NO_CAP     -> "Bean"
    Body.BROWN_BLACK_CAP  -> "Beanie"
    Body.BROWN_BROWN_CAP  -> "Bean"
    Body.ORANGE_NO_CAP    -> "Muffin"
    Body.ORANGE_BLACK_CAP -> "Muffiny"
    Body.ORANGE_BROWN_CAP -> "Muffin"
    Body.BUFF_NO_CAP      -> "Marshmallow"
    Body.BUFF_BLACK_CAP   -> "Marshmallow"
    Body.BUFF_BROWN_CAP   -> "Marshmallow"
    Body.GREEN_NO_CAP     -> "Mochi"
    Body.GREEN_BLACK_CAP  -> "Matcha"
    Body.GREEN_BROWN_CAP  -> "Mochi"
    Body.BLUE_NO_CAP      -> "Azure"
    Body.BLUE_BLACK_CAP   -> "Azura"
    Body.BLUE_BROWN_CAP   -> "Azure"
    Body.STEEL_NO_CAP     -> "Artax"
    Body.STEEL_BLACK_CAP  -> "Artaxa"
    Body.STEEL_BROWN_CAP  -> "Artax"
    Body.PURPLE_NO_CAP    -> "Bubbles"
    Body.PURPLE_BLACK_CAP -> "Bubbles"
    Body.PURPLE_BROWN_CAP -> "Bubbles"
    Body.PINK_NO_CAP      -> "Sprinkles"
    Body.PINK_BLACK_CAP   -> "Sprinkles"
    Body.PINK_BROWN_CAP   -> "Sprinkles"
  }

  private fun bodyToTitle(): String = when (body) {
    Body.BLACK_NO_CAP     -> "Sir"
    Body.BLACK_WHITE_CAP  -> "Dame"
    Body.WHITE_NO_CAP     -> "Sir"
    Body.WHITE_BLACK_CAP  -> "Dame"
    Body.WHITE_BROWN_CAP  -> "Dr."
    Body.GRAY_NO_CAP      -> "Sir"
    Body.GRAY_BLACK_CAP   -> "Dame"
    Body.GRAY_BROWN_CAP   -> "Dr."
    Body.BROWN_NO_CAP     -> "Sir"
    Body.BROWN_BLACK_CAP  -> "Dame"
    Body.BROWN_BROWN_CAP  -> "Dr."
    Body.ORANGE_NO_CAP    -> "Lady"
    Body.ORANGE_BLACK_CAP -> "Dame"
    Body.ORANGE_BROWN_CAP -> "Dr."
    Body.BUFF_NO_CAP      -> "Sir"
    Body.BUFF_BLACK_CAP   -> "Dame"
    Body.BUFF_BROWN_CAP   -> "Dr."
    Body.GREEN_NO_CAP     -> "Sir"
    Body.GREEN_BLACK_CAP  -> "Dame"
    Body.GREEN_BROWN_CAP  -> "Dr."
    Body.BLUE_NO_CAP      -> "Sir"
    Body.BLUE_BLACK_CAP   -> "Dame"
    Body.BLUE_BROWN_CAP   -> "Dr."
    Body.STEEL_NO_CAP     -> "Sir"
    Body.STEEL_BLACK_CAP  -> "Dame"
    Body.STEEL_BROWN_CAP  -> "Dr."
    Body.PURPLE_NO_CAP    -> "Sir"
    Body.PURPLE_BLACK_CAP -> "Dame"
    Body.PURPLE_BROWN_CAP -> "Dr."
    Body.PINK_NO_CAP      -> "Sir"
    Body.PINK_BLACK_CAP   -> "Dame"
    Body.PINK_BROWN_CAP   -> "Dr."
  }

  private fun feetToSuffix(): String = when (footSpots) {
    FootSpots.NO_SPOTS   -> "of Darkness"
    FootSpots.ALL        -> "of Light"
    FootSpots.FRONT_HALF -> "of War"
    FootSpots.BACK_HALF  -> "of Peace"
    FootSpots.FIRST      -> "of Winter"
    FootSpots.SECOND     -> "of Cthulhu"
    FootSpots.THIRD      -> "of Shadows"
    FootSpots.FOURTH     -> "Eater"
  }
}
