package org.mifos

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named

internal fun Project.configureDetekt(extension: DetektExtension) = extension.apply {
    tasks.named<Detekt>("detekt") {
        config.from(rootProject.file("config/detekt/detekt.yml"))
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)
            md.required.set(true)
        }
    }
    dependencies {
        "detektPlugins"(libs.findLibrary("detekt-formatting").get())
        "detektPlugins"(libs.findLibrary("twitter-detekt-compose").get())
    }
}