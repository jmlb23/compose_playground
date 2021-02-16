package com.github.jmlb23.mediumclone.state

import kotlinx.coroutines.flow.Flow


interface Store<S, A, E> {

    val dispatch : Dispatch<A>

    fun <T> select(selector: suspend (S) -> T): Flow<T>

    val subscribe: Flow<S>

    val state: S

    val enviroment: E

}

typealias Dispatch<A> = suspend (A) -> Unit

typealias Reducer<S, A> = (S, A) -> S

typealias Middleware<S, A, E> = (Store<S,A,E>) -> (Dispatch<A>) -> Dispatch<A>