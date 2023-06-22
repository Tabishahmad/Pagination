package com.witzeal.pagination.presentation.list

import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.text.style.TextAppearanceSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.witzeal.pagination.R
import com.witzeal.pagination.common.*
import com.witzeal.pagination.databinding.FragmentBookListBinding
import com.witzeal.pagination.domain.model.User
import com.witzeal.pagination.presentation.core.ViewState
import com.witzeal.pagination.presentation.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookListFragment : BaseFragment<BookListViewModel,FragmentBookListBinding>(
    R.layout.fragment_book_list
),ImageListAdapter.ItemClickListener {
    override val viewModel: BookListViewModel by activityViewModels()
    private val userPosition : Int = 35
    private var user: User? = null
    private var isUpdateList : Boolean = false
    private var firstVisibleItemPosition = 0
    override fun init() {
        setHasOptionsMenu(true)
        viewModel.fetchList()

    }

    override fun updateLayout() {
        val formatter = OrdinalSuperscriptFormatter(SpannableStringBuilder())
        formatter.format(binding.secodPos)
        formatter.format(binding.firstPos)
        formatter.format(binding.thirdPos)

        binding.tud.secodUserName.setText("Sneha")
        setStickyUser()
    }
    private fun setStickyUser(){
        if(user == null){
            return
        }
        binding.userTop.textRank.setText(""+user?.rank+"th")
        binding.userTop.textPoint.setText(""+user?.user_point)
        binding.userTop.textPriceMoney.setText(""+user?.price_money)

        binding.userBottom.textRank.setText(""+user?.rank+"th")
        binding.userBottom.textPoint.setText(""+user?.user_point)
        binding.userBottom.textPriceMoney.setText(""+user?.price_money)

        val formatter = OrdinalSuperscriptFormatter(SpannableStringBuilder())
        formatter.format(binding.userTop.textRank);
        formatter.format(binding.userBottom.textRank);

    }
    private fun hideProgressBar(){
        binding.progressBar.hide()
    }
    private fun showProgressBar(){
        binding.progressBar.show()
    }
    private fun setupRecyclersView(list:List<User>){
        hideProgressBar()
        if(list.size > userPosition){
            user = list.get(userPosition)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        if(isUpdateList == false){
            binding.recyclerView.setData(list)
        }else{
             binding.recyclerView.updateList(list){
                 binding.recyclerView.scrollToPosition(firstVisibleItemPosition)
             }
        }
        binding.recyclerView.setItemClickListener(this)
        handleScrolling()
    }
    private fun handleScrolling(){
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var isScrollingUp = false
            var lastVisibleItemPosition = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val currentVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (currentVisibleItemPosition > lastVisibleItemPosition) {
                    // Scrolling down
                    println("Scrolling down " + lastVisibleItemPosition)
                    isScrollingUp = false
                    if(lastVisibleItemPosition > userPosition) {
                        showTopUser()
                    }else{
                        UserHide()
                    }

                } else if (currentVisibleItemPosition < lastVisibleItemPosition) {
                    // Scrolling up
                    println("Scrolling up " + lastVisibleItemPosition)
                    isScrollingUp = true
                    if(lastVisibleItemPosition < userPosition) {
                        showBottomUser()
                    }else{
                        UserHide()
                    }

                }

                // Update last visible item position
                lastVisibleItemPosition = currentVisibleItemPosition

                // Handle start and end positions
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val itemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

//                if (isScrollingUp && firstVisibleItemPosition == 0) {
//                    fetchPreviousUsersData()
//                }
                if (!isScrollingUp && lastVisibleItemPosition == itemCount - 1) {
                    fetchNextUsersData(lastVisibleItemPosition)
                }
            }
        })
    }
    private fun showTopUser(){
        binding.userTop.layout.visibility = View.VISIBLE
        binding.userBottom.layout.visibility = View.GONE
    }
    private fun showBottomUser(){
        binding.userTop.layout.visibility = View.GONE
        binding.userBottom.layout.visibility = View.VISIBLE
    }
    private fun UserHide(){
        binding.userTop.layout.visibility = View.GONE
        binding.userBottom.layout.visibility = View.GONE
    }
    private fun fetchPreviousUsersData(){
        //Nothing to do
    }
    private fun fetchNextUsersData(offset : Int){
        isUpdateList = true
        viewModel.fetchNextUsersData(offset)
    }
    override fun onItemClick(view: View, any: Any, index: Int) {
        navigateToDetail(index)
    }
    private fun navigateToDetail(index: Int){
        val b = Bundle()
        b.putInt(LIST_INDEX,index)
        findNavController().navigate(R.id.bookDetailFragment,b)
    }
    private fun navigateToFavourite(){
        findNavController().navigate(R.id.favBookFragment)
    }

    override fun observeViewModel() {
        performCoroutineTask {
            viewModel.getviewStateFlow().collect{ viewState->
                when(viewState){
                    is ViewState.Loading ->
                        showProgressBar()
                    is ViewState.Success -> {
                        setupRecyclersView(viewState.result)
                    }
                    is ViewState.Failure -> {
                        hideProgressBar()
                        viewState.failMessage.let {
                            it.showCustomToast(requireContext())
                        }
                    }
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                navigateToFavourite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}