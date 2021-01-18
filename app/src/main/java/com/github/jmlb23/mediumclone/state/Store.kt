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

typealias Reducer<S, A> = (S, A) -> S
typealias Middleware<S, A, E> = suspend ((Store<S, A, E>, suspend (A) -> Unit, A) -> Unit)

fun <S, A, E> createStore(
    initalState: S,
    reducer: Reducer<S, A>,
    middleware: List<Middleware<S, A, E>>,
    appEnviroment: E
): Store<S, A, E> =
    object : Store<S, A, E> {

        private val _state = MutableStateFlow(initalState)

        private val _dispatch: (suspend (A) -> Unit) = middleware
            .reversed()
            .foldRight({ defaultDispatch(it) }) { middleware, dispatchFunction ->
                { x: A -> middleware(this, dispatchFunction, x) }
            }

        fun defaultDispatch(action: A) {
            this._state.value = reducer(this._state.value,action)
        }

        override suspend fun dispatch(action: A) {
            _dispatch(action)
        }

        override val subcribe: Flow<S> = _state

        override val enviroment: E =
            appEnviroment

        override fun getState(): S = _state.value
        override fun <T> select(selector: suspend (S) -> T): Flow<T> =
            _state.map(selector).distinctUntilChanged()


    }

