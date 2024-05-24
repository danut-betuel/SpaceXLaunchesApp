import androidx.compose.ui.window.ComposeUIViewController
import com.betuel.spacexlaunches.data.di.dataModule
import com.betuel.spacexlaunches.data.di.iOSDataModule
import com.betuel.spacexlaunches.presentation.di.viewModelModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(iOSDataModule, dataModule, viewModelModule)
        }
    }
)  { App() }