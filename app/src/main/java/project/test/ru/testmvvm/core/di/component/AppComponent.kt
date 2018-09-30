package project.test.ru.testmvvm.core.di.component

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import project.test.ru.testmvvm.App
import project.test.ru.testmvvm.core.di.module.NetworkModule
import project.test.ru.testmvvm.core.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,  ViewModelModule::class,  NetworkModule::class]
)
interface AppComponent {


    fun inject(app: App)
}