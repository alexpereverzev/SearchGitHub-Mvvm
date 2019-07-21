package project.test.ru.testmvvm

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import project.test.ru.testmvvm.core.di.component.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}