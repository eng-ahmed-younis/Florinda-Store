package com.florinda.store.ui.screens.search

interface SearchIntents {
    object GetOrders : SearchIntents
    class SearchByName (val queryName : String): SearchIntents
}