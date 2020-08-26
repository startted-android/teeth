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
}