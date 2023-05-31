package com.example.internetspeedchecker.prersentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internetspeedchecker.data.local.entity.SpeedEntity
import com.example.internetspeedchecker.data.repository.SpeedRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class SpeedViewModel @Inject constructor(
    private val repository: SpeedRepositoryImpl
) : ViewModel() {

    private val _avgUpSpeed = MutableLiveData<Int>()
    val avgUpSpeed = _avgUpSpeed
    private val _maxUpSpeed = MutableLiveData<Int>()
    val maxUpSpeed = _maxUpSpeed
    private val _minUpSpeed = MutableLiveData<Int>()
    val minUpSpeed = _minUpSpeed
    private val _currentUpSpeed = MutableLiveData<Int>()
    val currentUpSpeed = _currentUpSpeed


    private val _avgDownSpeed = MutableLiveData<Int>()
    val avgDownSpeed = _avgDownSpeed
    private val _maxDownSpeed = MutableLiveData<Int>()
    val maxDownSpeed = _maxDownSpeed
    private val _minDownSpeed = MutableLiveData<Int>()
    val minDownSpeed = _minDownSpeed
    private val _currentDownSpeed = MutableLiveData<Int>()
    val currentDownSpeed = _currentDownSpeed


    init {
        val timer = Timer()
        timer.schedule(10000, 10000) {
            getCurrDownSpeed()
            getMinDownSpeed()
            getMaxDownSpeed()
            getAvgDownSpeed()

            getAvgUpSpeed()
            getMinUpSpeed()
            getCurrUpSpeed()
            getMaxUpSpeed()
        }
    }


    private fun getAvgUpSpeed(){
        viewModelScope.launch {
            repository.getAvgUpLinkSpeed().collect {
                _avgUpSpeed.value = it
            }
        }
    }
    private fun getMaxUpSpeed(){
        viewModelScope.launch {
            repository.getMaxUpLinkSpeed().collect {
                _maxUpSpeed.value = it
            }
        }
    }
    private fun getMinUpSpeed(){
        viewModelScope.launch {
            repository.getMinUpLinkSpeed().collect {
                _minUpSpeed.value = it
            }
        }
    }
    private fun getCurrUpSpeed(){
        viewModelScope.launch {
            repository.getCurrUpLinkSpeed().collect {
                _currentUpSpeed.value = it
            }
        }
    }
    private fun getAvgDownSpeed(){
        viewModelScope.launch {
            repository.getAvgDownLinkSpeed().collect {
                _avgDownSpeed.value = it
            }
        }
    }
    private fun getMaxDownSpeed(){
        viewModelScope.launch {
            repository.getMaxDownLinkSpeed().collect {
                _maxDownSpeed.value = it
            }
        }
    }
    private fun getMinDownSpeed(){
        viewModelScope.launch {
            repository.getMinDownLinkSpeed().collect {
                _minDownSpeed.value = it
            }
        }
    }
    private fun getCurrDownSpeed(){
        viewModelScope.launch {
            repository.getCurrDownLinkSpeed().collect {
                _currentDownSpeed.value = it
            }
        }
    }




}