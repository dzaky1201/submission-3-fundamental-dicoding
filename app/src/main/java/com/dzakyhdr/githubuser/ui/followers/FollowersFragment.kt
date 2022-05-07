package com.dzakyhdr.githubuser.ui.followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyhdr.githubuser.MyApplication
import com.dzakyhdr.githubuser.databinding.FragmentFollowersBinding
import com.dzakyhdr.githubuser.ui.following.BaseRvAdapter
import com.google.android.material.snackbar.Snackbar

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FollowersViewModel> {
        FollowersViewModelFactory((activity?.application as MyApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getString("username")
        viewModel.getFollowers(args!!)

        val adapter = BaseRvAdapter()
        binding.rvFollowers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFollowers.adapter = adapter

        viewModel.loading.observe(viewLifecycleOwner){ loading ->
            if (loading){
                binding.loading.visibility = View.VISIBLE
            }else{
                binding.loading.visibility = View.GONE
            }
        }

        viewModel.followers.observe(viewLifecycleOwner) { following->
            adapter.submitList(following)
        }

        viewModel.errorStatus.observe(viewLifecycleOwner){text->
            text?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                viewModel.onSnackbarShown()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}