package com.creative.shadow.text.name.appiskey_androidapp_task.koin

import com.creative.shadow.text.name.appiskey_androidapp_task.vm.repository.WallpaperRepository
import com.creative.shadow.text.name.appiskey_androidapp_task.vm.viewModel.WallpaperViewViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

private val applicationScope = CoroutineScope(SupervisorJob())



private val modules= module {

    single { WallpaperViewViewModel() }
    single { WallpaperRepository() }
}


val moduleList= listOf(modules)