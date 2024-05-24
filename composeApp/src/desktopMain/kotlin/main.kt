import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.betuel.spacexlaunches.data.di.dataModule
import com.betuel.spacexlaunches.data.di.desktopDataModule
import com.betuel.spacexlaunches.presentation.di.viewModelModule
import org.koin.core.context.startKoin

val koin = startKoin {
    modules(desktopDataModule, dataModule, viewModelModule)
}

fun main() = application {

//    startKoin {
//        modules(desktopDataModule, dataModule, viewModelModule)
//    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "SpaceXLaunchesApp",
    ) {
        App()
    }
}