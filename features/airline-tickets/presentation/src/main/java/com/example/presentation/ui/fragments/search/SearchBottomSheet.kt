package com.example.presentation.ui.fragments.search

import android.os.Build
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_ui.base.BaseBottomSheetDialogFragment
import com.example.core_ui.estensions.safeNavigation
import com.example.core_ui.estensions.setCyrillicInputFilter
import com.example.core_ui.estensions.setupClearButtonWithAction
import com.example.core_ui.estensions.showShortToast
import com.example.core_ui.utils.MarginItemDecoration
import com.example.domain.model.HintButtonModel
import com.example.domain.model.RecommendedDestinationsModel
import com.example.presentation.R
import com.example.presentation.databinding.BottomSheetSearchBinding
import com.example.presentation.ui.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class SearchBottomSheet :
    BaseBottomSheetDialogFragment<BottomSheetSearchBinding, SearchViewModel>(
        R.layout.bottom_sheet_search,
        true
    ) {

    override val binding by viewBinding(BottomSheetSearchBinding::bind)
    override val viewModel by viewModel<SearchViewModel>()
    private val args by navArgs<SearchBottomSheetArgs>()

    private val hintButtonsAdapter: MainAdapter by lazy {
        MainAdapter(onHintButtonItemClick = ::onHintButtonItemClick)
    }

    private val recommendedDestinationsAdapter: MainAdapter by lazy {
        MainAdapter(onRecDestItemClick = ::onRecDestItemClick)
    }

    private val hintButtons: List<HintButtonModel> by lazy {
        listOf(
            HintButtonModel(R.drawable.img_hint_button_1, getString(R.string.title_hint_button_1)),
            HintButtonModel(R.drawable.img_hint_button_2, getString(R.string.title_hint_button_2)),
            HintButtonModel(R.drawable.img_hint_button_3, getString(R.string.title_hint_button_3)),
            HintButtonModel(R.drawable.img_hint_button_4, getString(R.string.title_hint_button_4))
        )
    }

    private val recommendedDestinations: List<RecommendedDestinationsModel> by lazy {
        listOf(
            RecommendedDestinationsModel(
                R.drawable.img_rec_dest_1,
                getString(R.string.title_rec_dest_1)
            ),
            RecommendedDestinationsModel(
                R.drawable.img_rec_dest_2,
                getString(R.string.title_rec_dest_2)
            ),
            RecommendedDestinationsModel(
                R.drawable.img_rec_dest_3,
                getString(R.string.title_rec_dest_3)
            ),
        )
    }

    override fun initialize() {
        binding.tvDepartureLocation.text = args.destination
        binding.etArrivalLocation.setupClearButtonWithAction(binding.btnClear)
        binding.etArrivalLocation.setCyrillicInputFilter()
        setupHintButtonsRv()
        setupRecommendedDestinationsRv()
    }

    override fun setupListeners() {
        binding.etArrivalLocation.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && binding.etArrivalLocation.text.isNotEmpty()) {
                viewModel.setDestinationFilled(true)
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun setupRecommendedDestinationsRv() {
        binding.rvRecommendedDestinations.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recommendedDestinationsAdapter
        }
        recommendedDestinationsAdapter.items = recommendedDestinations
    }

    private fun setupHintButtonsRv() {
        binding.rvHintButtons.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = hintButtonsAdapter
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(com.example.core_ui.R.dimen.item_hint_button_spacing)))
        }
        hintButtonsAdapter.items = hintButtons
    }

    private fun onHintButtonItemClick(title: String) {
        if (title == getString(R.string.title_hint_button_2)) {
            binding.etArrivalLocation.setText(title)
            viewModel.setDestinationFilled(true)
        } else {
            findNavController().safeNavigation(R.id.action_searchBottomSheet_to_emptyFragment)
        }
    }

    private fun onRecDestItemClick(title: String) {
        binding.etArrivalLocation.setText(title)
        viewModel.setDestinationFilled(true)
    }

    override fun launchObservers() {
        viewModel.isDestinationFilled.observe(this) { isFilled ->
            if (isFilled) {
                val departureLocation = binding.tvDepartureLocation.text.toString()
                val arrivalLocation = binding.etArrivalLocation.text.toString()
                findNavController().safeNavigation(
                    SearchBottomSheetDirections.actionSearchBottomSheetToFilledDestinationSearchFragment(
                        departureLocation,
                        arrivalLocation
                    )
                )
            }
        }
    }
}