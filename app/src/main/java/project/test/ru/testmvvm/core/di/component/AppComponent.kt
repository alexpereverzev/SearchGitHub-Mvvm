package project.test.ru.testmvvm.core.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import project.test.ru.testmvvm.App
import project.test.ru.testmvvm.core.di.module.NetworkModule
import project.test.ru.testmvvm.core.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,  ViewModelModule::class,  NetworkModule::class]
)
interface AppComponent : AndroidInjector<App> {
    override fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}