// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.mylib) apply false
    id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false
}
true // Needed to make the Suppress annotation work for the plugins block

//to use ksp
//https://developer.android.com/build/migrate-to-ksp#kts
//https://github.com/google/ksp/releases