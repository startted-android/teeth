import utils.withLibraries
import utils.withApiProjects

plugins {
    id("com.android.library")
    id("BuildPlugin")
}

withApiProjects(
    Modules.Libraries.globalMvvmRx
)

withLibraries(
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.appCompat
)