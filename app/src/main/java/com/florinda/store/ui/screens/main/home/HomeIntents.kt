package com.florinda.store.ui.screens.main.home

sealed interface HomeIntents {
    object GetCategories : HomeIntents
    data class GetOrdersByCategory(val categoryId : Int) : HomeIntents
}