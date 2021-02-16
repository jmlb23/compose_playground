package com.github.jmlb23.mediumclone.state

import kotlinx.coroutines.flow.*

fun <S, A, E> createStore(
    initalState: S,
    reducer: Reducer<S, A>,
    middleware: List<Middleware<S, A, E>>,
    appEnviroment: E
): Store<S, A, E> =
    object : Store<S, A, E> {

        private val _state = MutableStateFlow(initalState)

        private val _dispatch: Dispatch<A> =
            middleware.foldRight({ x -> defaultDispatch(x) }) { next, acc ->
                next(this)(acc)
            }


        suspend fun defaultDispatch(action: A) {
            _state.value = reducer(_state.value, action)
        }

        override val dispatch: Dispatch<A> = {
            _dispatch(it)
        }

        override val subscribe: Flow<S> = _state

        override fun <T> select(selector: suspend (S) -> T): Flow<T> =
            _state.map(selector)

        override val state: S
            get() = _state.value

        override val enviroment: E = appEnviroment


    }

fun <S, A, E> Store<S, A, E>.replaceDispatcher(dispatch: Dispatch<A>): Store<S, A, E> =
    object : Store<S, A, E> by this {
        override val dispatch: Dispatch<A>
            get() = dispatch
    }