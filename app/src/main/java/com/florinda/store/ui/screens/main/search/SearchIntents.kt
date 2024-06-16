package com.florinda.store.ui.screens.main.search

interface SearchIntents {
    object GetOrders : SearchIntents
    class SearchByName (val queryName : String): SearchIntents
}