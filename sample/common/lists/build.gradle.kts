import utils.withLibraries
import utils.withProjects
import utils.withKapts

plugins {
    id("BuildPlugin")
    id("com.android.library")
}

withProjects(
    Modules.Sample.Common.model,
    Modules.Sample.Common.resources
)

withLibraries(
    Libs.AndroidX.appCompat,
    Libs.AndroidX.Ktx.coreKtx
)