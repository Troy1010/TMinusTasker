package com.tminus1010.tminustasker.all_layers

import com.tminus1010.tminustasker.domain.LocalDateTimeToLocalDateWithAdjustmentConverter
import com.tminus1010.tminustasker.environment.EnvironmentModule
import dagger.Component

@Component(
    modules = [
        EnvironmentModule::class
    ]
)
interface AppComponent {

    fun localDateTimeToLocalDateWithAdjustmentConverter(): LocalDateTimeToLocalDateWithAdjustmentConverter

    @Component.Builder
    interface Builder {
        fun environmentModule(environmentModule: EnvironmentModule): Builder
        fun build(): AppComponent
    }
}