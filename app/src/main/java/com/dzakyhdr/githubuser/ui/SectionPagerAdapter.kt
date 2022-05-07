package com.dzakyhdr.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dzakyhdr.githubuser.ui.followers.FollowersFragment
import com.dzakyhdr.githubuser.ui.following.FollowingFragment

class SectionPagerAdapter(fm: FragmentActivity, private val username: String) :
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowingFragment()
                fragment.arguments = Bundle().apply {
                    putString("username", username)
                }
            }
            1 -> {
                fragment = FollowersFragment()
                fragment.arguments = Bundle().apply {
                    putString("username", username)
                }
            }
        }
        return fragment as Fragment
    }
}