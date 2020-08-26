import utils.withProjects
import utils.withLibraries

plugins {
    id("com.android.library")
    id("BuildPlugin")
}

withProjects(
    Modules.Sample.Mvvm.common
)

withLibraries(
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.appCompat,
    Libs.AndroidX.Ktx.viewModelKtx,
    Libs.cicerone
)