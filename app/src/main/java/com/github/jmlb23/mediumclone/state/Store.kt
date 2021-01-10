package com.github.jmlb23.mediumclone.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

interface Store<S, A, E> {
    val enviroment: E

    suspend fun dispatch(action: A)

    fun getState(): S

    fun <T> select(selector: suspend (S) -> T): Flow<T>

    val subcribe: Flow<S>
}


fun <S, A, E> createStore(
    initalState: S,
    middleware: (suspend (Store<S, A, E>, suspend (A) -> Unit, A) -> Unit),
    reducer: (S, A) -> S,
    appEnviroment: E
) =
    object : Store<S, A, E> {

        private val _state = MutableStateFlow(initalState)

        override suspend fun dispatch(action: A) {
            _state.emit(applyMiddlewareOrReducer(action, middleware))
        }


        private suspend fun applyMiddlewareOrReducer(
            action: A,
            middleware: (suspend (Store<S, A, E>, suspend (A) -> Unit, A) -> Unit)
        ): S {
            middleware(
                this,
                ::dispatch,
                action
            )
            return reducer(_state.value, action)

        }

        override val subcribe: Flow<S> = _state

        override val enviroment: E =
            appEnviroment

        override fun getState(): S = _state.value
        override fun <T> select(selector: suspend (S) -> T): Flow<T> = _state.map(selector).distinctUntilChanged()

    }

