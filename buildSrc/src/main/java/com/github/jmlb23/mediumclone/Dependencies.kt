package com.github.jmlb23.mediumclone

object Dependencies {

    object Versions {
        const val RetrofitVersion = "2.9.0"
        const val ComposeVersion = "1.0.0-beta01"
        const val ComposeNavigation = "1.0.0-alpha08"
        const val AndroidxCore = "1.3.2"
        const val AppCompat = "1.2.0"
        const val Lifecycle = "2.3.0-beta01"
        const val KotlinVersion = "1.4.30"
        const val KotlinSerialization = "1.0.1"
        const val KotlinDateTime = "0.1.0"
        const val AndroidxJunit = "1.1.2"
        const val Espresso = "3.3.0"
        const val Junit = "4.13.1"
        const val GoogleMaterial = "1.2.1"
        const val Interceptor = "4.9.0"
    }

    object Retrofit {
        private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RetrofitVersion}"
        private const val json = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
        private const val okHttpInterceptor ="com.squareup.okhttp3:logging-interceptor:${Versions.Interceptor}"

        val getAll = listOf(retrofit, json, okHttpInterceptor)
    }

    object Compose {
        private const val runtime =
            "androidx.compose.runtime:runtime:${Versions.ComposeVersion}"
        private const val ui = "androidx.compose.ui:ui:${Versions.ComposeVersion}"
        private const val material = "androidx.compose.material:material:${Versions.ComposeVersion}"
        private const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.ComposeVersion}"
        private const val navigation = "androidx.navigation:navigation-compose:${Versions.ComposeNavigation}"
        private const val foundation = "androidx.compose.foundation:foundation:${Versions.ComposeVersion}"
        val getAll = listOf(material, navigation, foundation, ui, uiTooling, runtime)

    }

    object Androidx {
        private const val activity = "androidx.activity:activity-compose:1.3.0-alpha02"
        private const val core = "androidx.core:core-ktx:${Versions.AndroidxCore}"
        private const val appCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
        private const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle}"

        val getAll = listOf(core, appCompat, lifecycle, activity)
    }

    object Jetbrains{
        private const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KotlinVersion}"
        private const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinSerialization}"
        private const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KotlinDateTime}"
        val getAll = listOf(stdlib, serialization, datetime)
    }

    object AndroidTest{
        private const val junit = "androidx.test.ext:junit:${Versions.AndroidxJunit}"
        private const val espresso = "androidx.test.espresso:espresso-core:${Versions.Espresso}"
        val getAll = listOf(junit, espresso)
    }

    object VanillaTest{
        private const val junit = "junit:junit:${Versions.Junit}"
        val getAll = listOf(junit)
    }

    object Google{
        private const val material = "com.google.android.material:material:${Versions.GoogleMaterial}"

        val getAll = listOf(material)
    }

    object Coil {
        private const val coil = "dev.chrisbanes.accompanist:accompanist-coil:0.6.0"
        val getAll = listOf(coil)
    }
}