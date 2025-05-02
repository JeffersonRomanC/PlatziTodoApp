package com.juandgaines.todoapp.presentation.screens.home

sealed class HomeScreenEvent {
    data object UpdatedTask: HomeScreenEvent()
    data object DeleteAllTask: HomeScreenEvent()
    data object DeletedTask: HomeScreenEvent()
}