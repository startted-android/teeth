import utils.withProjects
import utils.withLibraries
import utils.withKapts

plugins {
    id("com.android.application")
    id("BuildPlugin")
}

withProjects(
    Modules.Sample.Common.resources,
    Modules.Sample.Common.network,
    Modules.Sample.Mvvm.common,
    Modules.Sample.Mvvm.Features.articles,
    Modules.Sample.Mvvm.Features.mainFlow
)

withKapts(
    Libs.toothpickCompiler
)

withLibraries(
    Libs.cicerone,
    Libs.toothpickRuntime,
    Libs.AndroidX.Ktx.coreKtx,
    Libs.AndroidX.appCompat
)