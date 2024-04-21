package ru.practicum.android.diploma.ui.filter

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentAllFilterBinding
import ru.practicum.android.diploma.domain.debugLog
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared

class FilterAllFragment : Fragment() {

    private var _binding: FragmentAllFilterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<FilterAllViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.initFilterAllViewFilterState()
        Log.e("onAttach", "onAttach")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAllFilterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeApplyButtonVisible()
        observeFilterAllState()
        observeCountryState()
        observeIndustriesState()
        observeSalarySum()
        observeSalaryBoolean()
        bindTextWatcher()
        bindOnClickListeners()
        bindNavigationListeners()
    }

    private fun observeApplyButtonVisible() = with(binding) {
        viewModel.countryState.observe(viewLifecycleOwner) { country ->
            viewModel.regionState.observe(viewLifecycleOwner) { region ->
                viewModel.industriesState.observe(viewLifecycleOwner) { industries ->
                    viewModel.salarySum.observe(viewLifecycleOwner) { salaryText ->
                        viewModel.salaryBoolean.observe(viewLifecycleOwner) { salaryBoolean ->
                            filterFunctionApply.isVisible = viewModel.isEqualsCurrentFilters(
                                country,
                                region,
                                industries,
                                salaryText,
                                salaryBoolean,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun observeFilterAllState() = with(binding) {
        viewModel.countryState.observe(viewLifecycleOwner) { country ->
            viewModel.industriesState.observe(viewLifecycleOwner) { industries ->
                viewModel.salarySum.observe(viewLifecycleOwner) { salaryText ->
                    viewModel.salaryBoolean.observe(viewLifecycleOwner) { salaryBoolean ->
                        if (country != null || industries != null) {
                            filterFunctionRemove.visibility = View.VISIBLE
                        } else if (salaryText?.salary?.isNotEmpty() == true
                            || salaryBoolean != null
                        ) {
                            filterFunctionRemove.visibility = View.VISIBLE
                        } else {
                            filterFunctionRemove.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun observeCountryState() = with(binding) {
        viewModel.countryState.observe(viewLifecycleOwner) { country ->
            if (country != null) {
                filterTextWorkplace.text = country.countryName
                filterIcWorkplace.setImageResource(R.drawable.ic_close_24px)
                filterIcWorkplace.isClickable = true
                filterVisibleWorkplace.visibility = View.VISIBLE

                viewModel.regionState.observe(viewLifecycleOwner) { region ->
                    if (region != null) {
                        val text = "${country.countryName}, ${region.regionName}"
                        filterTextWorkplace.text = text
                    }
                }
            } else {
                filterTextWorkplace.text = "Место работы"
                filterTextWorkplace.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                filterIcWorkplace.setImageResource(R.drawable.arrow_forward_24px)
                filterIcWorkplace.isClickable = false
                filterVisibleWorkplace.visibility = View.GONE
            }
        }
    }

    private fun observeIndustriesState() = with(binding) {
        viewModel.industriesState.observe(viewLifecycleOwner) { industries ->
            if (industries != null) {
                filterTextIndustries.text = industries.industriesName
                filterIcIndustries.setImageResource(R.drawable.ic_close_24px)
                filterIcIndustries.isClickable = true
                filterVisibleIndustries.visibility = View.VISIBLE
            } else {
                filterTextIndustries.text = "Отрасль"
                filterTextIndustries.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                filterIcIndustries.setImageResource(R.drawable.arrow_forward_24px)
                filterIcIndustries.isClickable = false
                filterVisibleIndustries.visibility = View.GONE
            }
        }
    }

    private fun observeSalarySum() = with(binding) {
        viewModel.salarySum.observe(viewLifecycleOwner) { salarySum ->
            if (salarySum?.salary?.isNotEmpty() == true) {
                filterExpectedSalary.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_universal))
                filterTextSalary.setText(salarySum.salary)
                filterTextSalary.setSelection(salarySum.salary.length)
                filterSalaryClear.visibility = View.VISIBLE
            } else {
                filterExpectedSalary.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.all_filters_sum_hint)
                )
                filterTextSalary.setText("")
                filterSalaryClear.visibility = View.GONE
            }
        }
    }

    private fun observeSalaryBoolean() = with(binding) {
        viewModel.salaryBoolean.observe(viewLifecycleOwner) { salaryBoolean ->
            debugLog(TAG) { "filterFunctionCheckbox, isChecked = ${salaryBoolean != null}" }
            filterFunctionCheckbox.isChecked = salaryBoolean != null
        }
    }

    private fun bindOnClickListeners() = with(binding) {
        filterSalaryClear.setOnClickListener {
            filterTextSalary.setText("")
            viewModel.setSalarySumInfo(null)
        }

        filterFunctionCheckbox.setOnCheckedChangeListener { _, isChecked ->
            // Обновляем состояние флажка в viewModel
            viewModel.setSalaryBooleanInfo(
                if (isChecked) {
                    SalaryBooleanShared
                } else {
                    null
                }
            )
        }

        filterIcWorkplace.setOnClickListener {
            viewModel.setCountryInfo(null)
            viewModel.setRegionInfo(null)
        }

        filterIcIndustries.setOnClickListener {
            viewModel.setIndustriesInfo(null)
        }

        filterFunctionRemove.setOnClickListener {
            viewModel.setCountryInfo(null)
            viewModel.setRegionInfo(null)
            viewModel.setIndustriesInfo(null)
            viewModel.setSalarySumInfo(null)
            viewModel.setSalaryBooleanInfo(null)
        }
    }

    private fun bindTextWatcher() = with(binding) {
        filterTextSalary.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                filterTextSalary.clearFocus()
                hideKeyboard(textView)
                true
            } else {
                false
            }
        }

        filterTextSalary.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                /*NOTHING*/
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.filterSalaryClear.visibility = View.VISIBLE

                if (s?.isNotEmpty() == true) {
                    binding.filterSalaryClear.visibility = View.VISIBLE
                    binding.filterSalaryClear.isClickable = true
                    binding.filterFunctionRemove.visibility = View.VISIBLE
                } else {
                    binding.filterSalaryClear.visibility = View.GONE
                    binding.filterFunctionRemove.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {
                /*NOTHING*/
            }
        })

        filterTextSalary.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (filterTextSalary.text.isNotEmpty()) {
                    binding.filterExpectedSalary.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.black_universal)
                    )
                }
                viewModel.setSalarySumInfo(SalaryTextShared(salary = filterTextSalary.text.toString()))
            } else {
                binding.filterExpectedSalary.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.blue)
                )
            }
        }
    }

    private fun bindNavigationListeners() = with(binding) {
        filterFunctionApply.setOnClickListener {
            viewModel.updateSearchResults()
            findNavController().navigateUp()
        }

        filterWorkplace.setOnClickListener {
            findNavController().navigate(R.id.action_filterAllFragment_to_workplaceFragment)
        }

        filterIndustries.setOnClickListener {
            findNavController().navigate(R.id.action_filterAllFragment_to_industriesFragment)
        }

        filterToolbar.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun hideKeyboard(v: TextView) {
        val inputMethodManager = requireContext().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "FilterAllFragment"
    }
}
