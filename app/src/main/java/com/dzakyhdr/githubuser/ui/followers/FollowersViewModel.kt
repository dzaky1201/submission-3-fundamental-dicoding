package com.dzakyhdr.githubuser.ui.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzakyhdr.githubuser.data.remote.model.UserItem
import com.dzakyhdr.githubuser.data.repository.ErrorLoadData
import com.dzakyhdr.githubuser.data.repository.UserRepository
import kotlinx.coroutines.launch

class FollowersViewModel(private val repository: UserRepository) : ViewModel() {
    private var _followers = MutableLiveData<List<UserItem>>()
    val followers: LiveData<List<UserItem>> get() = _followers

    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun getFollowers(username: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _followers.value = repository.getFollowersUsers(username)
            } catch (error: ErrorLoadData) {
                _errorStatus.value = error.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun onSnackbarShown() {
        _errorStatus.value = null
    }
}