package ru.practicum.android.diploma.domain.filter.storage

import ru.practicum.android.diploma.domain.models.FiltersSettings

interface FiltersStorage {

    fun getPrefs(): FiltersSettings
    fun savePrefs(settings: FiltersSettings)
    fun clearPrefs()
}
