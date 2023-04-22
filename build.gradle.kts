import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
	kotlin("jvm") version "1.8.20"
	kotlin("plugin.serialization") version "1.8.20"
	id("net.minecraftforge.gradle") version "5.1.+"
	id("maven-publish")
	id("org.parchmentmc.librarian.forgegradle") version "1.+"
}

version = "0.1.0"
group = "dev.epicsquid"

val modid: String = "thatsrad"
archivesName.set(modid)

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

minecraft {
	mappings("parchment", "2022.11.27-1.19.2")

	runs {
		create("client") {
			workingDirectory(project.file("run"))

			property("forge.logging.markers", "REGISTRIES")
			property("forge.logging.console.level", "debug")
			property("forge.enabledGameTestNamespaces", modid)

			mods {
				create(modid) {
					source(sourceSets.main.get())
				}
			}
		}

		create("server") {
			workingDirectory(project.file("run"))

			property("forge.logging.markers", "REGISTRIES")
			property("forge.logging.console.level", "debug")
			property("forge.enabledGameTestNamespaces", modid)

			mods {
				create(modid) {
					source(sourceSets.main.get())
				}
			}
		}

		create("data") {
			workingDirectory(project.file("run"))

			property("forge.logging.markers", "REGISTRIES")
			property("forge.logging.console.level", "debug")

			args(
				"--mod", modid,
				"--all",
				"--output", file("src/generated/resources/"),
				"--existing", file("src/main/resources/")
			)

			mods {
				create(modid) {
					source(sourceSets.main.get())
				}
			}
		}
	}
}

sourceSets {
	main {
		resources.srcDirs("src/generated/resources")
	}
}

repositories {
	maven("https://thedarkcolour.github.io/KotlinForForge/")
}

dependencies {
	minecraft("net.minecraftforge:forge:1.19.2-43.2.8")
	implementation("thedarkcolour:kotlinforforge:3.11.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-Beta")
}

tasks.jar {
	manifest {
		attributes(
			"Specification-Title" to modid,
			"Specification-Vendor" to "epicsquid",
			"Specification-Version" to "1",
			"Implementation-Title" to project.name,
			"Implementation-Version" to project.version,
			"Implementation-Vendor" to "epicsquid"
		)
	}
}

tasks.jar {
	finalizedBy("reobfJar")
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}
