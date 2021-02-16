package com.github.jmlb23.mediumclone.state

import android.util.Log
import kotlinx.coroutines.flow.*

fun <S : Any, A : Any, E> createStore(
    initalState: S,
    reducer: Reducer<S, A>,
    middleware: List<Middleware<S, A, E>>,
    appEnviroment: E
): Store<S, A, E> =
    object : Store<S, A, E> {

        private val _state = MutableStateFlow(initalState)

        private val _dispatch: Dispatch<A> =
            middleware.reversed().fold({ x -> defaultDispatch(x) }) { acc, next ->
                next(this.replaceDispatcher(acc))(acc)
            }


        suspend fun defaultDispatch(action: A) {
            _state.emit(reducer(_state.value, action))
        }

        override val dispatch: Dispatch<A> = _dispatch


        override val subscribe: Flow<S> = _state

        override fun <T> select(selector: suspend (S) -> T): Flow<T> =
            _state.map(selector)

        override val state: S
            get() = _state.value

        override val enviroment: E = appEnviroment


    }

fun <S, A, E> Store<S, A, E>.replaceDispatcher(dispatch: Dispatch<A>): Store<S, A, E> =
    object : Store<S, A, E> by this {
        override val dispatch: Dispatch<A> = dispatch
    }