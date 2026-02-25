package com.roborev.nekogen

@Suppress("HardCodedStringLiteral")
object CatSerializer {
  fun start() = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n<map>\n    <int name=\"food\" value=\"0\" />\n    <float name=\"water\" value=\"0.0\" />\n"
  fun serialize(cat: Cat) = "    <string name=\"cat:${cat.seed}\">${cat.name}</string>\n"
  fun end() = "</map>\n"
}