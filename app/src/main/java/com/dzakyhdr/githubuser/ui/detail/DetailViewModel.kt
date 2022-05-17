package com.dzakyhdr.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzakyhdr.githubuser.data.local.UserEntity
import com.dzakyhdr.githubuser.data.remote.model.UserItem
import com.dzakyhdr.githubuser.data.repository.ErrorLoadData
import com.dzakyhdr.githubuser.data.repository.UserRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: UserRepository) : ViewModel() {

    private var _detail = MutableLiveData<UserItem>()
    val detail: LiveData<UserItem> get() = _detail

    private var _errorStatus = MutableLiveData<String?>()
    val errorStatus: LiveData<String?> get() = _errorStatus

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite get() = _isFavorite

    private val listFavoriteUsers: MutableLiveData<List<UserEntity>?> = MutableLiveData()

    init {
        getFavoriteUsers()
        _isFavorite.postValue(false)
    }

    fun getDetail(username: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _detail.value = repository.getDetail(username)
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

    fun showUserIsFavorite(favoriteUser: UserItem) {
        viewModelScope.launch {
            for (it in listFavoriteUsers.value ?: mutableListOf()) {
                if (favoriteUser.login == it.login) {
                    _isFavorite.postValue(true)
                    break
                } else {
                    _isFavorite.postValue(false)
                }
            }
        }
    }

    fun checkFavoriteUser(favoriteUser: UserItem) {
        viewModelScope.launch {
            if (_isFavorite.value == true) {
                repository.delete(favoriteUser)
                getFavoriteUsers()
                _isFavorite.postValue(false)
            } else {
                repository.insertUser(favoriteUser)
                getFavoriteUsers()
                _isFavorite.postValue(true)
            }
        }
    }

    private fun getFavoriteUsers() {
        viewModelScope.launch {
            listFavoriteUsers.value = repository.getUsers()
        }
    }
}