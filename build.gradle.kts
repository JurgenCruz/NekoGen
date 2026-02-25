plugins {
  idea
  alias(libs.plugins.kotlin.jvm) apply false
}

subprojects {
  group = "com.roborev"
  version = "1.0.0"
}

