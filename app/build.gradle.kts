plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.ozanyazici.retrofitkotlin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ozanyazici.retrofitkotlin"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}
dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")// Retrofit kütüphanesi, REST API'leri çağırmak için kullanılır.
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")//Retrofit için Gson dönüştürücüsü, JSON verilerini Java objelerine dönüştürmek için kullanılır.
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")//Bu bağımlılık, Retrofit tarafından yapılan ağ çağrılarını RxJava'da kullanılan Observable türünde verilere dönüştürmenizi sağlar.

    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")//RxJava'nın temel bağımlılığı.
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.1")// RxAndroid kütüphanesi, Android ile RxJava'nın uyumlu kullanımını sağlar.
    implementation ("androidx.recyclerview:recyclerview:1.3.2")//Recyclerview bağımlılığı.
}