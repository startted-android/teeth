package com.startted.global_mvvm

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Single

val PAGING_ITEMS_COUNT = 20

open class Paginator<T>(
    private val getPage: (Int, Int) -> Single<List<T>>,
    private val getItem: ((Int) -> Single<T>)? = null,
    private val idMapper: ((T) -> Int)? = null,
    private val PAGE_ITEMS_COUNT: Int = PAGING_ITEMS_COUNT,
    bootstrapper: Set<Observable<Wish>> = setOf(),
    isAutoLoadFirstPageEnabled: Boolean = true
) : BaseViewModel() {

    val stateLiveData = MutableLiveData<State<T>>(State.Null())
    val errorLiveData = SingleLiveEvent<Throwable>()

    init {
        if (isAutoLoadFirstPageEnabled) loadMore()
        bootstrapper.forEach {
            it.subscribe {
                accept(it)
            }.connect()
        }
    }

    fun accept(wish: Wish) {
        when (wish) {
            is Wish.LoadMore -> loadMore()
            is Wish.Refresh -> refresh()
            is Wish.UpdateItem -> updateItem(wish.id)
            is Wish.DeleteItem -> deleteItem(wish.id)
            is Wish.UpdateVisibleItems -> updateVisibleItems(wish.addedItemsCount)
        }
    }

    private fun loadMore() {
        when (val state = stateLiveData.value) {
            is State.Null -> {
                State.EmptyProgress<T>().setAsCurrent()
                getPage.invoke(1, PAGE_ITEMS_COUNT)
                    .subscribe(
                        {  list ->
                            getSuccessState(
                                list = list,
                                ifShort = State.FullData(list, 1),
                                ifEmpty = State.Empty(),
                                ifElse = State.Data(list, 1)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(listOf<T>(), 1, it).setAsCurrent()
                        }
                    ).connect()
            }
            is State.Data -> {
                val loadingPageNumber = state.loadedPageNumber + 1
                State.NewPageProgress<T>(loadingPageNumber).setAsCurrent()
                getPage.invoke(loadingPageNumber, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = State.FullData(state.data, loadingPageNumber),
                                ifShort = State.FullData(state.data + list, loadingPageNumber),
                                ifElse = State.Data(state.data + list, loadingPageNumber)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(state.data, loadingPageNumber, it).setAsCurrent()
                        }
                    ).connect()
            }
            is State.PageError -> {
                if (state.data.isEmpty())
                    State.EmptyProgress<T>().setAsCurrent()
                else
                    State.NewPageProgress<T>(state.errorPageNumber).setAsCurrent()
                getPage.invoke(state.errorPageNumber, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = if (state.data.isEmpty()) State.Empty() else State.FullData(
                                    state.data + list,
                                    state.errorPageNumber
                                ),
                                ifShort = State.FullData(state.data + list, state.errorPageNumber),
                                ifElse = State.Data(state.data + list, state.errorPageNumber)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(state.data, state.errorPageNumber, it).setAsCurrent()
                        }
                    ).connect()
            }
            is State.Empty -> {
                State.EmptyProgress<T>().setAsCurrent()
                getPage.invoke(1, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = State.Empty<T>(),
                                ifShort = State.FullData(list, 1),
                                ifElse = State.Data(list, 1)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(listOf<T>(), 1, it).setAsCurrent()
                        }
                    ).connect()
            }
        }
    }

    private fun refresh() {
        when (val state = stateLiveData.value) {
            is State.Null -> {
                State.RefreshProgress<T>().setAsCurrent()
                getPage.invoke(1, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = State.Empty<T>(),
                                ifShort = State.FullData(list, 1),
                                ifElse = State.Data(list, 1)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(listOf<T>(), 1, it).setAsCurrent()
                        }
                    ).connect()
            }
            is State.FullData -> {
                State.RefreshProgress<T>().setAsCurrent()
                getPage.invoke(1, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = State.Empty(),
                                ifShort = State.FullData(list, 1),
                                ifElse = State.Data(list, 1)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(state.data, 1, it).setAsCurrent()
                        }
                    ).connect()
            }
            is State.Data -> {
                State.RefreshProgress<T>().setAsCurrent()
                getPage.invoke(1, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = State.Empty(),
                                ifShort = State.FullData(list, 1),
                                ifElse = State.Data(list, 1)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(state.data, 1, it).setAsCurrent()
                        }
                    ).connect()
            }
            is State.PageError -> {
                State.RefreshProgress<T>().setAsCurrent()
                getPage.invoke(state.errorPageNumber, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = State.Empty(),
                                ifShort = State.FullData(list, state.errorPageNumber),
                                ifElse = State.Data(list, 1)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(state.data, state.errorPageNumber, it).setAsCurrent()
                        }
                    ).connect()
            }
            is State.Empty -> {
                State.RefreshProgress<T>().setAsCurrent()
                getPage.invoke(1, PAGE_ITEMS_COUNT)
                    .subscribe(
                        { list ->
                            getSuccessState(
                                list = list,
                                ifEmpty = State.Empty(),
                                ifShort = State.FullData(list, 1),
                                ifElse = State.Data(list, 1)
                            ).setAsCurrent()
                        },
                        {
                            errorLiveData.postValue(it)
                            State.PageError(listOf<T>(), 1, it).setAsCurrent()
                        }
                    ).connect()
            }
        }
    }

    private fun updateVisibleItems(addedItemsCount: Int = 0) {
        when (val state = stateLiveData.value) {
            is State.Data -> {
                getPage.invoke(1, state.loadedPageNumber.times(PAGE_ITEMS_COUNT) + addedItemsCount)
                    .subscribe(
                        {
                            if (it.isNullOrEmpty()) {
                                State.Empty<T>().setAsCurrent()
                            } else {
                                State.Data(it, state.loadedPageNumber).setAsCurrent()
                            }
                        },
                        {}
                    ).connect()
            }
            is State.FullData ->
                getPage.invoke(1, state.loadedPageNumber.times(PAGE_ITEMS_COUNT) + addedItemsCount)
                    .subscribe(
                        {
                            if (it.isNullOrEmpty()) {
                                State.Empty<T>().setAsCurrent()
                            } else {
                                State.FullData(it, state.loadedPageNumber).setAsCurrent()
                            }
                        },
                        {}
                    ).connect()
            is State.PageError -> loadMore()
            is State.Empty -> loadMore()
        }
    }

    private fun updateItem(itemId: Int) {
        when (val state = stateLiveData.value) {
            is State.Data ->
                getItem?.invoke(itemId)
                    ?.subscribe(
                        { newItem ->
                            State.Data(
                                state.data.map { if (idMapper?.invoke(it) == itemId) newItem else it },
                                state.loadedPageNumber
                            ).setAsCurrent()
                        },
                        {}
                    )?.connect()
            is State.FullData ->
                getItem?.invoke(itemId)
                    ?.subscribe(
                        { newItem ->
                            State.FullData(
                                state.data.map { if (idMapper?.invoke(it) == itemId) newItem else it },
                                state.loadedPageNumber
                            ).setAsCurrent()
                        },
                        {}
                    )?.connect()
            is State.PageError ->
                getItem?.invoke(itemId)
                    ?.subscribe(
                        { newItem ->
                            State.Data(
                                state.data.map { if (idMapper?.invoke(it) == itemId) newItem else it },
                                state.errorPageNumber - 1
                            ).setAsCurrent()
                        },
                        {}
                    )?.connect()
        }
    }

    private fun deleteItem(itemId: Int) {
        when (val state = stateLiveData.value) {
            is State.Data ->
                state.data.filter { idMapper?.invoke(it) != itemId }.let {
                    if (it.isNullOrEmpty())
                        State.Empty<T>().setAsCurrent()
                    else {
                        State.Data(
                            it,
                            state.loadedPageNumber
                        ).setAsCurrent()
                    }
                }

            is State.FullData ->
                state.data.filter { idMapper?.invoke(it) != itemId }.let {
                    if (it.isNullOrEmpty())
                        State.Empty<T>().setAsCurrent()
                    else {
                        State.FullData(
                            it,
                            state.loadedPageNumber
                        ).setAsCurrent()
                    }
                }
            is State.PageError ->
                state.data.filter { idMapper?.invoke(it) != itemId }.let {
                    if (it.isNullOrEmpty())
                        state
                    else {
                        State.Data(
                            it,
                            state.errorPageNumber - 1
                        ).setAsCurrent()
                    }
                }
        }
    }

    sealed class State<T> {
        class Null<T> : State<T>()
        class EmptyProgress<T> : State<T>()
        data class NewPageProgress<T>(val loadingPageNumber: Int) : State<T>()
        class RefreshProgress<T> : State<T>()
        data class Data<T>(
            val data: List<T>,
            val loadedPageNumber: Int
        ) : State<T>()
        data class FullData<T>(
            val data: List<T>,
            val loadedPageNumber: Int
        ) : State<T>()
        data class PageError<T>(
            val data: List<T>,
            val errorPageNumber: Int,
            val throwable: Throwable

        ) : State<T>()
        class Empty<T> : State<T>()
    }

    private fun State<T>.setAsCurrent() {
        stateLiveData.postValue(this)
    }

    private fun getSuccessState(
        list: List<T>,
        ifEmpty: State<T>,
        ifShort: State<T>,
        ifElse: State<T>
    ) =
        when {
            list.isEmpty() -> ifEmpty
            list.size < PAGE_ITEMS_COUNT -> ifShort
            else -> ifElse
        }

    sealed class Wish {
        object LoadMore : Wish()
        object Refresh : Wish()
        data class UpdateItem(val id: Int) : Wish()
        data class DeleteItem(val id: Int) : Wish()
        data class UpdateVisibleItems(val addedItemsCount: Int = 0) : Wish()
    }

}