import utils.withLibraries
import utils.withProjects
import utils.withKapts

plugins {
    id("com.android.library")
    id("BuildPlugin")
}

withLibraries(
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.appCompat,
    Libs.AndroidX.material,
    Libs.toothpickRuntime,
    Libs.cicerone
)

withKapts(
    Libs.toothpickCompiler
)