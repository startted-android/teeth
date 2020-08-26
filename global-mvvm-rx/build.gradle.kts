import utils.withLibraries
import utils.withProjects
import utils.withKapts

plugins {
    id("BuildPlugin")
    id("com.android.library")
}

withProjects(
    Modules.Libraries.global
)

withLibraries(
    Libs.AndroidX.appCompat,
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.lifecycleExtensions,
    Libs.AndroidX.Ktx.viewModelKtx,
    Libs.rxKotlin,
    Libs.rxAndroid
)