package com.github.jmlb23.mediumclone.state

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface Store<S, A, E> {
    val enviroment: E

    suspend fun dispatch(action: A)

    fun getState(): S

    fun <T> select(selector: suspend (S) -> T): Flow<T>

    val subcribe: Flow<S>
}

typealias Reducer<S, A> = (S, A) -> S
typealias Middleware<S, A, E> = ((Store<S, A, E>, suspend (A) -> Unit, A) -> Job)

fun <S, A, E> createStore(
    initalState: S,
    reducer: Reducer<S, A>,
    middleware: Middleware<S, A, E>,
    appEnviroment: E
): Store<S, A, E> =
    object : Store<S, A, E> {

        private val _state = MutableStateFlow(initalState)

        override suspend fun dispatch(action: A) {
            _state.emit(applyMiddlewareOrReducer(action, middleware))
        }


        private fun applyMiddlewareOrReducer(
            action: A,
            middleware: Middleware<S, A, E>?
        ): S {
            return middleware?.let { x ->
                middleware(this, { }, action)
                reducer(_state.value, action)
            } ?: reducer(_state.value, action)

        }

        override val subcribe: Flow<S> = _state

        override val enviroment: E =
            appEnviroment

        override fun getState(): S = _state.value
        override fun <T> select(selector: suspend (S) -> T): Flow<T> =
            _state.map(selector)


    }

