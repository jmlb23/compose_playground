package com.github.jmlb23.mediumclone

import kotlinx.coroutines.flow.*

interface AsyncStore<S, A, E> {

	suspend fun dispatch(action: A)

	val state: Flow<S>
}

//typealias AsyncMiddleware<S, A, E> = suspend (AsyncStore<S, A>, (Flow<S>, Flow<A>, E) -> Flow<S>, A) -> Flow<S>
//
//fun <S, A, E> combineAsyncMiddleware(vararg middleware: AsyncMiddleware<S, A, E>): AsyncMiddleware<S, A, E> {
//	return { store, reducer, action ->
//		middleware
//				.map { it(store, reducer, action) }
//				.reduce { acc, s -> reducer(store.state, flowOf(action),store.env) }
//	}
//}

fun <S, A, E> createAsyncStore(
		def: S,
		reducer: (Flow<S>, Flow<A>, E) -> Flow<S> ,
		env: E
): AsyncStore<S, A, E> =
		object : AsyncStore<S, A, E> {

			private val s: MutableStateFlow<S> = MutableStateFlow(def)

			override suspend fun dispatch(action: A) {
				val currentState = reducer(s, flowOf(action),env)
				s.value = currentState.first()
			}

//			private suspend fun applyAsyncMiddlewareOrReducer(
//					action: A,
//					middleware: AsyncMiddleware<S, A, E>?
//			): Flow<S> {
//				return if (middleware == null)
//					reducer(s, flowOf(action),env)
//				else middleware(
//						this,
//						reducer,
//						action
//				)
//			}

			override val state: Flow<S> = s

		}