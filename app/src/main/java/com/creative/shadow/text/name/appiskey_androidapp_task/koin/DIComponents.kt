package com.creative.shadow.text.name.appiskey_androidapp_task.koin

import com.creative.shadow.text.name.appiskey_androidapp_task.vm.repository.WallpaperRepository
import com.creative.shadow.text.name.appiskey_androidapp_task.vm.viewModel.WallpaperViewViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object DIComponents : KoinComponent {

 val wallpaperViewViewModel by inject<WallpaperViewViewModel>()
 val wallpaperRepository by inject<WallpaperRepository>()
}