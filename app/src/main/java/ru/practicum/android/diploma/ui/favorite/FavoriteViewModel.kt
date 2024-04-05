package ru.practicum.android.diploma.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel : ViewModel() {

    private var state = MutableLiveData<FavoriteUpdate>(
        FavoriteUpdate.EmptyVacancyList
    )

    fun getState(): LiveData<FavoriteUpdate> {
        return state
    }
}