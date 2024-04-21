package ru.practicum.android.diploma.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.debugLog
import ru.practicum.android.diploma.domain.filter.FilterRepositoryCountryFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryIndustriesFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryRegionFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryBooleanFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryTextFlow
import ru.practicum.android.diploma.domain.filter.FilterUpdateFlowRepository
import ru.practicum.android.diploma.domain.filter.datashared.CountryShared
import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared
import ru.practicum.android.diploma.domain.filter.datashared.RegionShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared
import ru.practicum.android.diploma.ui.filter.model.FilterAllViewFilterState

class FilterAllViewModel(
    private val filterRepositoryCountryFlow: FilterRepositoryCountryFlow,
    private val filterRepositoryRegionFlow: FilterRepositoryRegionFlow,
    private val filterRepositoryIndustriesFlow: FilterRepositoryIndustriesFlow,
    private val filterRepositorySalaryTextFlow: FilterRepositorySalaryTextFlow,
    private val filterRepositorySalaryBooleanFlow: FilterRepositorySalaryBooleanFlow,
    private val filterUpdateFlowRepository: FilterUpdateFlowRepository,
) : ViewModel() {

    private var _countryState = MutableLiveData<CountryShared?>()
    val countryState: LiveData<CountryShared?> = _countryState

    private val _regionState = MutableLiveData<RegionShared?>()
    val regionState: LiveData<RegionShared?> = _regionState

    private val _industriesState = MutableLiveData<IndustriesShared?>()
    val industriesState: LiveData<IndustriesShared?> = _industriesState

    private val _salarySum = MutableLiveData<SalaryTextShared?>()
    val salarySum: LiveData<SalaryTextShared?> = _salarySum

    private val _salaryBoolean = MutableLiveData<SalaryBooleanShared?>()
    val salaryBoolean: LiveData<SalaryBooleanShared?> = _salaryBoolean

    private var filterAllViewFilterState = FilterAllViewFilterState()

    init {
        initSubscribe()
    }

    fun initFilterAllViewFilterState() {
        filterAllViewFilterState = FilterAllViewFilterState(
            filterRepositoryCountryFlow.getCountryFlow().value,
            filterRepositoryRegionFlow.getRegionFlow().value,
            filterRepositoryIndustriesFlow.getIndustriesFlow().value,
            filterRepositorySalaryTextFlow.getSalaryTextFlow().value,
            filterRepositorySalaryBooleanFlow.getSalaryBooleanFlow().value,
        )
    }

    fun isEqualsCurrentFilters(
        country: CountryShared?,
        region: RegionShared?,
        industries: IndustriesShared?,
        salaryText: SalaryTextShared?,
        salaryBoolean: SalaryBooleanShared?
    ): Boolean = filterAllViewFilterState.country != country ||
        filterAllViewFilterState.region != region ||
        filterAllViewFilterState.industries != industries ||
        filterAllViewFilterState.salaryText != salaryText ||
        filterAllViewFilterState.salaryBoolean != salaryBoolean

    private fun initSubscribe() {
        with(viewModelScope) {
            launch {
                filterRepositoryCountryFlow.getCountryFlow()
                    .collect { country ->
                        debugLog(TAG) { "filterRepositoryCountryFlow, collect: country = $country" }
                        _countryState.postValue(country)
                    }
            }

            launch {
                filterRepositoryRegionFlow.getRegionFlow()
                    .collect { region ->
                        debugLog(TAG) { "filterRepositoryRegionFlow, collect: region = $region" }
                        _regionState.postValue(region)
                    }
            }

            launch {
                filterRepositoryIndustriesFlow.getIndustriesFlow()
                    .collect { industries ->
                        debugLog(TAG) { "filterRepositoryIndustriesFlow, collect: industries = $industries" }
                        _industriesState.postValue(industries)
                    }
            }

            launch {
                filterRepositorySalaryTextFlow.getSalaryTextFlow()
                    .collect { salarySum ->
                        debugLog(TAG) { "filterRepositorySalaryTextFlow, collect: salarySum = $salarySum" }
                        _salarySum.postValue(salarySum)
                    }
            }

            launch {
                filterRepositorySalaryBooleanFlow.getSalaryBooleanFlow()
                    .collect { salaryBoolean ->
                        debugLog(TAG) { "filterRepositorySalaryBooleanFlow, collect: salaryBoolean = $salaryBoolean" }
                        _salaryBoolean.postValue(salaryBoolean)
                    }
            }
        }
    }

    fun updateSearchResults() {
        viewModelScope.launch {
            filterUpdateFlowRepository.emitFlow()
        }
    }

    fun setCountryInfo(country: CountryShared?) {
        filterRepositoryCountryFlow.setCountryFlow(country)
    }

    fun setRegionInfo(region: RegionShared?) {
        filterRepositoryRegionFlow.setRegionFlow(region)
    }

    fun setIndustriesInfo(industries: IndustriesShared?) {
        filterRepositoryIndustriesFlow.setIndustriesFlow(industries)
    }

    fun setSalarySumInfo(salary: SalaryTextShared?) {
        filterRepositorySalaryTextFlow.setSalaryTextFlow(salary)
    }

    fun setSalaryBooleanInfo(salary: SalaryBooleanShared?) {
        filterRepositorySalaryBooleanFlow.setSalaryBooleanFlow(salary)
    }

    companion object {
        private const val TAG = "FilterAllViewModel"
    }
}
