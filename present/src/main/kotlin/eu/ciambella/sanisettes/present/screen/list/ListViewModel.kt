package eu.ciambella.sanisettes.present.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.ciambella.sanisettes.design.core.scaffold.ScaffoldProperty
import eu.ciambella.sanisettes.domain.sanisette.usecase.GetSanisettesUseCase
import eu.ciambella.sanisettes.domain.settings.usecase.ChangeOnlyFilterEnableUseCase
import eu.ciambella.sanisettes.domain.settings.usecase.PmrOnlyFilterEnableUseCase
import eu.ciambella.sanisettes.domain.utils.CoroutineDispatcherProvider
import eu.ciambella.sanisettes.present.common.navigation.Action
import eu.ciambella.sanisettes.present.common.navigation.ActionHandler
import eu.ciambella.sanisettes.present.common.navigation.EventActionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    private val listScreenMapper: ListScreenMapper,
    private val getSanisettesUseCase: GetSanisettesUseCase,
    private val pmrOnlyFilterEnableUseCase: PmrOnlyFilterEnableUseCase,
    private val changeOnlyFilterEnableUseCase: ChangeOnlyFilterEnableUseCase,
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val actionHandler: ActionHandler,
) : ViewModel(), EventActionHandler {

    private val model = MutableStateFlow(
        ListState()
    )

    val state: StateFlow<ScaffoldProperty> = model.map {
        mapToUI(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        listScreenMapper.loading(this)
    )

    private fun mapToUI(
        state: ListState
    ): ScaffoldProperty = listScreenMapper.map(
        state = state,
        eventActionHandler = this,
        onNextPageRequested = ::requestNextPageData,
        onPmrFilterValueChange = ::onPmrFilterValueChange
    )

    fun create() {
        requestFirstPageData()
        listenPmrFilterChange()
    }

    fun onLocationPermissionGranted() {
        requestFirstPageData()
    }

    private fun listenPmrFilterChange() {
        viewModelScope.launch(dispatcherProvider.io) {
            pmrOnlyFilterEnableUseCase.invoke().collect { enabled ->
                model.update {
                    it.copy(
                        pmrFilterEnable = enabled,
                    )
                }
            }
        }
    }

    private fun requestFirstPageData() {
        viewModelScope.launch(dispatcherProvider.io) {
            val result = getSanisettesUseCase.invoke()
            val sanisettes = result.getOrNull()
            model.update {
                it.copy(
                    firstSanisettesResult = result,
                    sanisettes = sanisettes?.sanisettes ?: emptyList(),
                    nextOffset = sanisettes?.nextOffset,
                )
            }
        }
    }

    private fun requestNextPageData(nextOffset: Int) {
        viewModelScope.launch(dispatcherProvider.io) {
            val result = getSanisettesUseCase.invoke(nextOffset)
            val sanisettes = result.getOrNull()
            model.update {
                it.copy(
                    nextSanisettesResult = result,
                    sanisettes = it.sanisettes + (sanisettes?.sanisettes ?: emptyList()),
                    nextOffset = sanisettes?.nextOffset
                )
            }
        }
    }

    private fun onPmrFilterValueChange(newValue: Boolean) {
        viewModelScope.launch(dispatcherProvider.io) {
            changeOnlyFilterEnableUseCase.invoke(newValue)
        }
    }

    override fun handle(action: Action) {
        actionHandler.handle(action)
    }
}
