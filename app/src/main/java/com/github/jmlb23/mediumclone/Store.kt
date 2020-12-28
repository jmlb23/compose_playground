package com.github.jmlb23.mediumclone

import java.util.concurrent.atomic.AtomicReference

interface Store<S, A> {

    data class Subscription(val code: Int)

    fun dispatch(action: A)

    fun subscribe(subscriber: (S) -> Unit): Subscription

    fun dispose(subscription: Subscription)

    fun getState(): S
}

typealias Middleware<S, A> = (Store<S, A>, (S, A) -> S, A) -> S

fun <S, A> combineMiddleware(vararg middleware: Middleware<S, A>): Middleware<S, A> {
    return { store, reducer, action ->
        middleware
            .map { it(store, reducer, action) }
            .reduce { acc, s -> reducer(store.getState(), action) }
    }
}

fun <S,A> combineReducers(vararg reducer: (S, A) -> S): (S,A) -> S{
    return {s, a ->
        reducer.reduce{acc, function -> {s, a -> function(acc(s,a),a) } }(s,a)
    }
}

fun <S, A> createStore(
    def: S,
    reducer: (S, A) -> S,
    middleware: Middleware<S, A>? = null
): Store<S, A> =
    object : Store<S, A> {

        private val state = AtomicReference(def)

        private val listeners: MutableMap<Store.Subscription, (S) -> Unit> = mutableMapOf()

        override fun dispatch(action: A) {
            val currentState = applyMiddlewareOrReducer(action, middleware)
            state.set(currentState)
            listeners.forEach {
                it.value(currentState)
            }
        }

        private fun applyMiddlewareOrReducer(
            action: A,
            middleware: Middleware<S, A>?
        ): S {
            return if (middleware == null)
                reducer(state.get(), action)
            else middleware(
                this,
                reducer,
                action
            )
        }

        override fun subscribe(subscriber: (S) -> Unit): Store.Subscription {
            val subscription = Store.Subscription(subscriber.hashCode())
            listeners[subscription] = subscriber
            return subscription
        }

        override fun dispose(subscription: Store.Subscription) {
            listeners.remove(subscription)
        }

        override fun getState(): S =
            state.get()
    }


