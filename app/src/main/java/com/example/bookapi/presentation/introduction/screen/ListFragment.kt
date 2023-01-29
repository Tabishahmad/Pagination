package com.example.bookapi.presentation.introduction.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookapi.data.repository.model.toBook
import com.example.bookapi.databinding.FragmentListBinding
import com.example.bookapi.domain.usecase.datamodel.Book
import com.example.bookapi.presentation.introduction.BaseFragment
import com.example.bookapi.presentation.introduction.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : BaseFragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.fetchList().collect {
                it.data?.toBook()?.let { it1 -> setupScreen(it1) }
            }
        }
    }
    private fun setupScreen(list:List<Book>){
        binding.progressBar.visibility = View.GONE
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rv.setData(list)
//        binding.rv.setItemClickListener(this)
    }
}