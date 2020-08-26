import utils.withProjects
import utils.withLibraries

plugins {
    id("com.android.library")
    id("BuildPlugin")
}

withProjects(
    Modules.Sample.Mvvm.common,
    Modules.Sample.Common.network,
    Modules.Sample.Common.model,
    Modules.Sample.Common.lists,
    Modules.Sample.Common.resources
)

withLibraries(
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.appCompat,
    Libs.AndroidX.Ktx.viewModelKtx
)