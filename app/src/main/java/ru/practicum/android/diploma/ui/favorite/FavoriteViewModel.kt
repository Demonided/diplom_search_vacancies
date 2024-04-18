package ru.practicum.android.diploma.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.favorite.FavoriteInteractor
import ru.practicum.android.diploma.domain.models.VacancyDetails
import java.io.IOException

class FavoriteViewModel(
    private val favoriteInteractor: FavoriteInteractor
) : ViewModel() {

    private var _state = MutableLiveData<FavoriteState>(
        FavoriteState.EmptyList
    )

    fun getState(): LiveData<FavoriteState> = _state

    fun reloadFavoriteVacancies() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                val data = ArrayList<VacancyDetails>()
                favoriteInteractor.getAllFavoriteVacancies().collect {
                    data.add(it)
                }
                if (data.size > 0) {
                    _state.postValue(FavoriteState.VacancyList(data))
                } else {
                    _state.postValue(FavoriteState.EmptyList)
                }
            } catch (e: IOException) {
                Log.e("Exception", e.message.toString())
                _state.postValue(FavoriteState.Error)
            }
        }
    }
}
