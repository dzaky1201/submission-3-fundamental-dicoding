package com.dzakyhdr.githubuser.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dzakyhdr.githubuser.R
import com.dzakyhdr.githubuser.databinding.FragmentDetailBinding
import com.dzakyhdr.githubuser.ui.SectionPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getString("username")

        viewModel.getDetail(data ?: "")

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }


        viewModel.detail.observe(viewLifecycleOwner) { detail ->
            viewModel.showUserIsFavorite(detail)
            binding.apply {
                txtUsername.text = detail.login
                txtCompany.text = getString(R.string.company, detail.company)
                txtCountFollowers.text = detail.followers.toString()
                txtCountFollowing.text = detail.following.toString()
                txtFullname.text = getString(R.string.fullname, detail.name)
                if (detail.location == null) {
                    txtLocation.text = getString(R.string.location, "Not Found")
                } else {
                    txtLocation.text = getString(R.string.location, detail.location)
                }
                txtRepository.text = getString(R.string.repository, detail.publicRepos.toString())
                Glide.with(view.context).load(detail.avatarUrl).into(binding.imgAvatar)

                favorite.setOnClickListener {
                    viewModel.checkFavoriteUser(detail)
                }
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        view.context,
                        R.drawable.ic_baseline_favorite_24
                    )
                )
            } else {
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        view.context,
                        R.drawable.ic_baseline_favorite_border_24
                    )
                )
            }
        }

        viewModel.errorStatus.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                viewModel.onSnackbarShown()
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(requireActivity(), data ?: "")
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.following,
            R.string.followers
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}