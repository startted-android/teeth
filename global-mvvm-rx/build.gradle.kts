import utils.withApis
import utils.withLibraries
import utils.withApiProjects

plugins {
    id("com.android.library")
    id("BuildPlugin")
}

withApiProjects(
    Modules.Libraries.global
)

withApis(
    Libs.cicerone
)

withLibraries(
    Libs.AndroidX.appCompat,
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.lifecycleExtensions,
    Libs.AndroidX.Ktx.viewModelKtx,
    Libs.rxKotlin,
    Libs.rxAndroid
)