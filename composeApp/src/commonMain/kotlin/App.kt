import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.betuel.spacexlaunches.presentation.pages.rocketLaunch.RocketLaunchScreen
import com.betuel.spacexlaunches.presentation.pages.rocketLaunch.RocketLaunchViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "RocketLaunch"
            ) {
                composable("RocketLaunch") {
                    val viewModel = koinViewModel<RocketLaunchViewModel>()
                    val state by viewModel.state.collectAsState()
                    RocketLaunchScreen(state, viewModel::loadLaunches)
                }
            }
        }
    }
}

@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}