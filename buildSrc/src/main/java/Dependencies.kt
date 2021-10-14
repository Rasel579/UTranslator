import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "com.professional"
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 31
    val javaVersion = JavaVersion.VERSION_1_8
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val historyScreen = ":historyScreen"

}

object Versions {
    const val rxJava = "3.0.0"
    const val rxJavaAndroid = "3.0.0"
    const val rxJavaKotlin = "3.0.0"
    const val retrofit = "2.9.0"
    const val retrofitConverterGson = "2.9.0"
    const val retrofitAdapterRxJava = "2.9.0"
    const val recycleView = "1.2.0"
    const val loging = "4.9.1"
    const val glide = "4.11.0"
    const val roomKapt = "2.3.0"
    const val room = "2.3.0"
    const val roomKotlin = "2.3.0"
    const val roomRxJava = "2.3.0"
    const val viewBinding = "1.4.7"
    const val dagger = "2.37"
    const val daggerAndroid = "2.37"
    const val daggerAndroidSupport = "2.37"
    const val daggerKapt = "2.37"
    const val daggerKaptAndroid = "2.37"
    const val koin = "3.1.2"
    const val koinAndroid = "3.1.2"
    const val koinAndroidCompat = "3.1.2"
    const val coroutine = "1.5.1"
    const val coroutineAndroid = "1.5.1"
    const val corektx = "1.6.0"
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.1"
    const val legacySupport = "1.0.0"
    const val junit = "4.13.2"
    const val extJunit = "1.1.3"
    const val espresso = "3.4.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recycleView = "androidx.recyclerview:recyclerview:${Versions.recycleView}"

}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.corektx}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
}

object RxJava {
    const val rxJavaAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxJavaAndroid}"
    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxJavaKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxJavaKotlin}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
    const val adapterRxjava =
        "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitAdapterRxJava}"
    const val loging = "com.squareup.okhttp3:logging-interceptor:${Versions.loging}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Room {
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKotlin = "androidx.room:room-ktx:${Versions.roomKotlin}"
    const val roomRxJava = "androidx.room:room-rxjava3:${Versions.roomRxJava}"
    const val kaptRoom = "androidx.room:room-compiler:${Versions.roomKapt}"
}

object ViewBinding {
    const val viewBinding =
        "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBinding}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.daggerAndroid}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.daggerAndroidSupport}"
    const val kaptDagger = "com.google.dagger:dagger-compiler:${Versions.daggerKapt}"
    const val kaptAndroidDagger = "com.google.dagger:dagger-android-processor:${Versions.daggerKaptAndroid}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koinAndroidCompat}"
}

object Coroutines {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroid}"
}

object Tests {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitImpl = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

