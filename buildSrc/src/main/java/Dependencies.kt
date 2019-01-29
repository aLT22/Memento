object Dependencies {
    /**
     * Kotlin
     * */
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    val coroutines = arrayOf(
        coroutinesCore,
        coroutinesAndroid
    )

    /**
     * JetPack dependencies
     * */
    const val appCompat = "androidx.appcompat:appcompat:${Versions.JetPack.appCompat}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.JetPack.constraint}"
    val jetPack = arrayOf(
        "androidx.appcompat:appcompat:${Versions.JetPack.appCompat}",
        "androidx.constraintlayout:constraintlayout:${Versions.JetPack.constraint}"
    )
    const val navigationUi = "android.arch.navigation:navigation-ui:${Versions.JetPack.navigation}"
    const val navigationFragment = "android.arch.navigation:navigation-fragment:${Versions.JetPack.navigation}"
    val navigationJetPack = arrayOf(
        navigationUi,
        navigationFragment
    )

    /**
     * DI (Koin)
     * */
    const val koinAndroid = "org.koin:koin-android:${Versions.DI.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.DI.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.DI.koin}"
    val koins = arrayOf(
        "org.koin:koin-android:${Versions.DI.koin}",
        "org.koin:koin-androidx-scope:${Versions.DI.koin}",
        "org.koin:koin-androidx-viewmodel:${Versions.DI.koin}"
    )

    /**
     * Tests
     * */
    const val jUnit = "junit:junit:${Versions.Tests.jUnit}"
    const val runner = "androidx.test:runner:${Versions.Tests.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.Tests.espresso}"
}