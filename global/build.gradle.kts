import utils.withLibraries
import utils.withProjects
import utils.withKapts

plugins {
    id("com.android.library")
    id("BuildPlugin")
}

withProjects(
    Modules.Sample.Common.resources
)

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