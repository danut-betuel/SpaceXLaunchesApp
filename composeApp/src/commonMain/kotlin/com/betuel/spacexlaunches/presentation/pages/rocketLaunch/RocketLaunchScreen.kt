package com.betuel.spacexlaunches.presentation.pages.rocketLaunch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.betuel.spacexlaunches.domain.entity.RocketLaunch
import com.betuel.spacexlaunches.presentation.extensions.format
import com.betuel.spacexlaunches.presentation.theme.app_theme_successful
import com.betuel.spacexlaunches.presentation.theme.app_theme_unsuccessful
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketLaunchScreen(
    state: RocketLaunchScreenState,
    onRefresh: () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        onRefresh()
        pullToRefreshState.endRefresh()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "SpaceX Launches",
                    style = MaterialTheme.typography.headlineLarge
                )
            })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Loading...", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn {
                    items(state.launches) { launch: RocketLaunch ->
                        Column(modifier = Modifier.padding(all = 16.dp)) {
                            val launchDateUTC = launch.launchDateUTC.toInstant().toLocalDateTime(TimeZone.UTC)
                            Text(
                                text = "${launch.missionName} - ${launchDateUTC.format("MM-dd-yyyy")}",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (launch.launchSuccess == true) "Successful" else "Unsuccessful",
                                color = if (launch.launchSuccess == true) app_theme_successful else app_theme_unsuccessful
                            )
                            Spacer(Modifier.height(8.dp))
                            val details = launch.details
                            if (details?.isNotBlank() == true) {
                                Text(
                                    text = details
                                )
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }

            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}