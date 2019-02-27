object Versions {

    /**
     * DefaultApp configs
     * */
    //App-module config
    const val compile = 28
    const val applicationId = "com.bytebuilding.memento"
    const val minSdk = 15
    const val targetSdk = 28
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val proguard = "proguard-android.txt"
    const val proguardRules = "proguard-rules.pro"

    //Data module config
    const val compileData = 28
    const val minSdkData = 15
    const val targetSdkData = 28
    const val versionCodeData = 1
    const val versionNameData = "1.0"
    const val proguardData = "proguard-android.txt"
    const val proguardRulesData = "proguard-rules.pro"

    //Domain module config
    const val compatibility = "8"

    /**
     * Kotlin
     * */
    object Kotlin {
        const val kotlin = "1.3.11"
        const val coroutines = "1.0.1"
    }

    /**
     * JetPack
     * */
    object JetPack {
        const val appCompat = "1.1.0-alpha01"
        const val constraint = "1.1.3"
        const val navigation = "1.0.0-alpha11"
        const val room = "2.0.0"
    }

    /**
     * DI (Koin)
     * */
    object DI {
        const val koin = "1.0.2"
    }

    /**
     * Misc
     * */
    object Misc {
        const val circleImageView = "3.0.0"
    }

    /**
     * Tests
     * */
    object Tests {
        const val jUnit = "4.12"
        const val runner = "1.1.1"
        const val espresso = "3.1.1"
    }
}