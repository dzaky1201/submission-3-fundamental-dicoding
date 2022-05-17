package com.dzakyhdr.githubuser.ui.home

import androidx.lifecycle.*
import com.dzakyhdr.githubuser.SettingPreference
import com.dzakyhdr.githubuser.data.remote.model.UserItem
import com.dzakyhdr.githubuser.data.repository.ErrorLoadData
import com.dzakyhdr.githubuser.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository, private val pref: SettingPreference) :
    ViewModel() {

    private var _users = MutableLiveData<List<UserItem>>()
    val users: LiveData<List<UserItem>> get() = _users

    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun getSearchUser(username: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _users.value = repository.getSearchUsers(username)
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

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}