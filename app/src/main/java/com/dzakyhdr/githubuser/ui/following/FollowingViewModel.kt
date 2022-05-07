package com.dzakyhdr.githubuser.ui.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzakyhdr.githubuser.data.model.UserItem
import com.dzakyhdr.githubuser.data.repository.ErrorLoadData
import com.dzakyhdr.githubuser.data.repository.UserRepository
import kotlinx.coroutines.launch

class FollowingViewModel(private val repository: UserRepository): ViewModel() {
    private var _following = MutableLiveData<List<UserItem>>()
    val following: LiveData<List<UserItem>> get() = _following

    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun getFollowing(username: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _following.value = repository.getFollowingUsers(username)
            } catch (error: ErrorLoadData){
                _errorStatus.value = error.message
            }finally {
                _loading.value = false
            }
        }
    }

    fun onSnackbarShown() {
        _errorStatus.value = null
    }
}