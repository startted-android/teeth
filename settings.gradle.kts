rootProject.name = "teeth"

registerModules(
    *Modules.libraries,
    *Modules.applications,
    *Modules.features
)

fun Settings.registerModules(vararg libs: ProjectBean) {
    libs.forEach {
        include(it.name)

        if (it.path.isNotEmpty()) {
            project(it.name).projectDir = File(rootDir, it.path)
        }
    }
}

class ProjectBean(
    val name: String,
    val path: String
)

object Modules {

    object Applications {
        const val mvvmSampleApp = ":sample:mvvm:app"
    }

    object Sample {

        object Common {
            const val model = ":sample:common:model"
            const val network = ":sample:common:network"
            const val resources = ":sample:common:resources"
            const val lists = ":sample:common:lists"
        }

        object Mvvm {

            const val common = ":sample:mvvm:common"

            object Features {
                const val mainFlow = ":sample:mvvm:features:mainflow"
                const val articles = ":sample:mvvm:features:articles"
            }

        }

    }

    object Libraries {
        const val global = ":global"
        const val globalMvvmRx = ":global-mvvm-rx"
    }

    val applications
        get() = arrayOf(ProjectBean(Applications.mvvmSampleApp, "sample/mvvm/app"))

    val features
        get() = arrayOf(
            ProjectBean(Sample.Mvvm.Features.mainFlow, "sample/mvvm/features/feature-mainflow"),
            ProjectBean(Sample.Mvvm.Features.articles, "sample/mvvm/features/feature-articles")
        )

    val libraries
        get() = arrayOf(
            ProjectBean(Libraries.global, "global"),
            ProjectBean(Libraries.globalMvvmRx, "global-mvvm-rx"),
            ProjectBean(Sample.Common.model, "sample/common/model"),
            ProjectBean(Sample.Common.network, "sample/common/network"),
            ProjectBean(Sample.Common.resources, "sample/common/resources"),
            ProjectBean(Sample.Common.lists, "sample/common/lists"),
            ProjectBean(Sample.Mvvm.common, "sample/mvvm/common")
        )
}