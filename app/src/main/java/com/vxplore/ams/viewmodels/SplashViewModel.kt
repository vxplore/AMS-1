package com.vxplore.ams.viewmodels


import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.debduttapanda.j3lib.ActivityService
import com.debduttapanda.j3lib.DataIds
import com.debduttapanda.j3lib.Navigation
import com.debduttapanda.j3lib.NotificationService
import com.debduttapanda.j3lib.PermissionHandler
import com.debduttapanda.j3lib.Resolver
import com.debduttapanda.j3lib.ResultingActivityHandler
import com.debduttapanda.j3lib.SoftInputMode
import com.debduttapanda.j3lib.StatusBarColor
import com.debduttapanda.j3lib.WirelessViewModelInterface
import com.debduttapanda.j3lib.scope
import com.vxplore.ams.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(

) : WirelessViewModelInterface, ViewModel() {
    override val navigation = Navigation()
    override val permissionHandler = PermissionHandler()
    override val resultingActivityHandler = ResultingActivityHandler()
    private val _statusBarColor = mutableStateOf<StatusBarColor?>(null)
    override val softInputMode = mutableStateOf(SoftInputMode.adjustNothing)
    override val resolver = Resolver()
    override val notifier = NotificationService { id, arg ->
        when (id) {
        }
    }
    init {
        resolver.addAll(
            DataIds.statusBarColor to _statusBarColor,
        )
        _statusBarColor.value = StatusBarColor(
            color = Color.White,
            darkIcons = true
        )
        setup()
        //login_check()
    }
    private fun setup() {
        viewModelScope.launch {

            delay(3000)
            navigation.scope { navHostController, lifecycleOwner, activityService ->
                navHostController.navigate(Routes.login.full)
            }
        }
    }


}
