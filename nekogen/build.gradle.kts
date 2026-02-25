plugins {
  application
  idea
  jacoco
  alias(libs.plugins.kotlin.jvm)
}
val mockitoAgent = configurations.create("mockitoAgent")
dependencies {
  repositories(RepositoryHandler::mavenCentral)
  implementation(libs.coroutines)
  testImplementation(libs.bundles.test)
  testRuntimeOnly(libs.junit.engine)
  mockitoAgent(libs.mockito) { isTransitive = false }
}

application {
  mainClass = "com.roborev.nekogen.ApplicationKt"
}

val runningDir = project.file("./build/run/")

tasks {
  withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
  }
  named<JavaExec>("run") {
    workingDir = runningDir
    dependsOn("createRunDir")
  }
  test {
    useJUnitPlatform()
    testLogging {
      events("PASSED", "FAILED", "SKIPPED")
    }
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
  }
  compileJava {
    targetCompatibility = "23"
  }
  compileTestJava {
    targetCompatibility = "23"
  }
  compileKotlin {
    compilerOptions {
      jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_23)
    }
  }
  compileTestKotlin {
    compilerOptions {
      jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_23)
    }
  }
  register("createRunDir") {
    doLast {
      runningDir.mkdirs()
    }
    dependsOn("compileJava")
  }
  jacocoTestReport {
    dependsOn(test)
  }
  check {
    dependsOn(jacocoTestReport)
  }
}
