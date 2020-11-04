package com.evaluation.favorites.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.evaluation.R
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.adapter.viewholder.item.CardItemView
import com.evaluation.databinding.FavoriteLayoutBinding
import com.evaluation.utils.autoCleared
import com.evaluation.favorites.viewmodel.FavoriteViewModel
import com.evaluation.utils.DEFAULT_POSITION
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
@AndroidEntryPoint
class FavoriteFragment : Fragment(), AdapterItemClickListener<BaseItemView> {

    private var binding: FavoriteLayoutBinding by autoCleared()

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_layout, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRootView()
        initLoader()
    }

    private fun initRootView() {
        binding.listView.listener = this
    }

    override fun onClicked(item: BaseItemView) {
        when (item) {
            is CardItemView -> { viewModel.deleteFavorite(item.repository) }
        }
    }

    private fun initLoader() {
        viewModel.items.observe(viewLifecycleOwner) {
            binding.listView.adapter.items = it
        }
    }

}