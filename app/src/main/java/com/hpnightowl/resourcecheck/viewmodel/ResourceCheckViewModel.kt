package com.hpnightowl.resourcecheck.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpnightowl.resourcecheck.repository.ResourceRepository
import com.hpnightowl.resourcecheck.repository.ResourceResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UiState(
    val resourceName: String = "",
    val selectedResourceType: String = "bool",
    val result: ResourceResult? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ResourceCheckViewModel(
    private val repository: ResourceRepository = ResourceRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setResourceName(name: String) {
        _uiState.value = _uiState.value.copy(
            resourceName = name, errorMessage = null
        )
    }

    fun setResourceType(type: String) {
        _uiState.value = _uiState.value.copy(
            selectedResourceType = type, errorMessage = null
        )
    }

    fun checkResource(context: Context) {
        if (_uiState.value.resourceName.isBlank()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Please enter a resource name"
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                val result = repository.getOverlayResource(
                    context = context,
                    resourceName = _uiState.value.resourceName,
                    resourceType = _uiState.value.selectedResourceType
                )

                _uiState.value = _uiState.value.copy(
                    result = result, isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error checking resource: ${e.message}", isLoading = false
                )
            }
        }
    }

    fun clearResult() {
        _uiState.value = _uiState.value.copy(result = null, errorMessage = null)
    }

    fun getSupportedTypes(): List<String> = repository.getAllSupportedTypes()
}