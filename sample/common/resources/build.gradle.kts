import utils.withKapts
import utils.withLibraries
import utils.withProjects
import utils.withApis

plugins {
    id("com.android.library")
    id("BuildPlugin")
}

withLibraries(
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.appCompat
)

withApis(
    Libs.AndroidX.material
)