package com.florinda.store.ui.screens.main.setting

import androidx.lifecycle.ViewModel
import com.florinda.store.ui.theme.AppTheme
import com.florinda.store.ui.utils.local.LanguageItem
import kotlinx.coroutines.flow.MutableStateFlow

class SettingViewModel : ViewModel() {


    private var _themeUserSetting = MutableStateFlow(AppTheme.MODE_AUTO)
    val themeUserSetting = _themeUserSetting



    fun getCurrentLanguage(): LanguageItem {
        /*   var local: String = LanguageItem.EN.value
           viewModelScope.launch {
               dataStoreManager?.getPreference(DataStoreKeys.APP_LANGUAGE, LanguageItem.EN.value)
                   ?.collect {
                       local = it
                   }
           }

           val lang = LanguageItem.entries.firstOrNull {
               it.value == local
           }*/
        return  LanguageItem.EN
    }

}