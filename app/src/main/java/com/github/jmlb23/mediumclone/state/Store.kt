package com.github.jmlb23.mediumclone.state

import kotlinx.coroutines.flow.*

fun <S : Any, A : Any, E> createStore(
    initalState: S,
    reducer: Reducer<S, A>,
    middleware: List<Middleware<S, A, E>>,
    appEnviroment: E
): Store<S, A, E> =
    object : Store<S, A, E> {

        private val _state = MutableStateFlow(initalState)

        private val _dispatch: Dispatch<A> by lazy {
            middleware.reversed().fold({ x -> defaultDispatch(x) }) { acc, next ->
                next { _dispatch(it) }(this)(acc)
            }
        }

        suspend fun defaultDispatch(action: A) {
            val state = reducer(_state.value, action)
            _state.emit(state)
        }

        override val dispatch: Dispatch<A> = _dispatch


        override val subscribe: Flow<S> = _state

        override fun <T> select(selector: suspend (S) -> T): Flow<T> =
            _state.map(selector)

        override fun state(): S = _state.value

        override val enviroment: E = appEnviroment


    }

fun <S, A, E> Store<S, A, E>.replaceDispatcher(dispatch: Dispatch<A>): Store<S, A, E> =
    object : Store<S, A, E> by this {
        override val dispatch: Dispatch<A> = dispatch
    }