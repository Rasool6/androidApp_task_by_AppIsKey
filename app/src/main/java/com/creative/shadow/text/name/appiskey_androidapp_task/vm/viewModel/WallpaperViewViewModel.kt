package com.creative.shadow.text.name.appiskey_androidapp_task.vm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
 import com.creative.shadow.text.name.appiskey_androidapp_task.koin.DIComponents.wallpaperRepository
import com.creative.shadow.text.name.appiskey_androidapp_task.model.WallpaperModel2

class WallpaperViewViewModel : ViewModel() {


    val muteLivedata = MutableLiveData<List<WallpaperModel2>>()

    fun getWallpaperData(category: String): MutableLiveData<List<WallpaperModel2>> {
        wallpaperRepository.fetchWallpaper(category).observeForever { data ->
            muteLivedata.value = data
        }
        return muteLivedata
    }

}