package com.github.jmlb23.mediumclone

object Dependencies {

    object Versions {
        const val RetrofitVersion = "2.9.0"
        const val ComposeVersion = "1.0.0-alpha09"
        const val ComposeNavigation = "1.0.0-alpha04"
        const val AndroidxCore = "1.3.2"
        const val AppCompat = "1.2.0"
        const val Lifecycle = "2.3.0-beta01"
        const val KotlinVersion = "1.4.21"
        const val KotlinSerialization = "1.0.1"
        const val KotlinDateTime = "0.1.0"
        const val AndroidxJunit = "1.1.2"
        const val Espresso = "3.3.0"
        const val Junit = "4.13.1"
        const val GoogleMaterial = "1.2.1"
        const val Redux = "1.0.4"
        const val Interceptor = "4.9.0"
    }

    object Retrofit {
        private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RetrofitVersion}"
        private const val gson = "com.squareup.retrofit2:converter-gson:${Versions.RetrofitVersion}"
        private const val okHttpInterceptor ="com.squareup.okhttp3:logging-interceptor:${Versions.Interceptor}"

        val getAll = listOf(retrofit, gson, okHttpInterceptor)
    }

    object Compose {
        private const val runtime =
            "androidx.compose.runtime:runtime-rxjava2:${Versions.ComposeVersion}"
        private const val ui = "androidx.compose.ui:ui:${Versions.ComposeVersion}"
        private const val material = "androidx.compose.material:material:${Versions.ComposeVersion}"
        private const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.ComposeVersion}"
        private const val navigation = "androidx.navigation:navigation-compose:${Versions.ComposeNavigation}"

        val getAll = listOf(runtime, ui, material, uiTooling, navigation)

    }

    object Androidx {
        private const val core = "androidx.core:core-ktx:${Versions.AndroidxCore}"
        private const val appCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
        private const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle}"

        val getAll = listOf(core, appCompat, lifecycle)
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
        private const val coil = "dev.chrisbanes.accompanist:accompanist-coil:0.4.0"
        val getAll = listOf(coil)
    }

    object Redux{
        private const val redux = "org.rekotlin:rekotlin:${Versions.Redux}"
        val getAll = listOf(redux)
    }
}