
plugins {
    id ("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id ("kotlin-kapt")

}
android {
    compileSdk = 30

    defaultConfig {
        applicationId = Config.applicationId
        minSdk =  Config.minSdk
        targetSdk  = Config.targetSdk
        versionCode = Releases.versionCode
        versionName  = Releases.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            getDefaultProguardFile("proguard-android-optimize.txt")
            "proguard-rules.pro"

        }
    }
    compileOptions {
        sourceCompatibility (JavaVersion.VERSION_1_8)
        targetCompatibility (JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    /**Modules**/
    implementation(project(":utils"))
    implementation(project(":repository"))
    implementation(project(":model"))
    implementation(project(":core"))
    implementation(project(":historyfeature"))
    implementation(project(":descriptionfeature"))
    implementation(project(":favoritefeature"))


    /**RxJava2 **/
    implementation (RxJava.rxJava)
    implementation (RxJava.rxJavaAndroid)
    implementation (RxJava.rxJavaKotlin)
    /**Retrofit**/
    implementation (Retrofit.adapterRxjava)
    implementation (Retrofit.converterGson)
    implementation (Retrofit.loging)
    implementation (Retrofit.retrofit)



    /**Room**/
    implementation (Room.room)
    implementation (Room.roomKotlin)
    implementation (Room.roomRxJava)
    kapt (Room.kaptRoom)


    /**view binding delegate**/
    implementation (ViewBinding.viewBinding)
    /**Dagger 2**/
    implementation (Dagger.dagger)
    implementation (Dagger.daggerAndroid)
    implementation (Dagger.daggerAndroidSupport)
    kapt (Dagger.kaptAndroidDagger)
    kapt (Dagger.kaptDagger)

    /**Koin*/
    implementation (Koin.koinCore)
    implementation (Koin.koinAndroid)
    implementation (Koin.koinAndroidCompat)

    /**Coroutines*/
    implementation (Coroutines.coroutinesCore)
    implementation (Coroutines.coroutinesAndroid)


    implementation (Design.recycleView)
    implementation (Kotlin.core)
    implementation (Design.appcompat)
    implementation (Design.material)
    implementation (Design.constraintLayout)
    implementation (Kotlin.legacySupport)
    testImplementation (Tests.junit)
    androidTestImplementation (Tests.junitImpl)
    androidTestImplementation (Tests.espresso)
}

