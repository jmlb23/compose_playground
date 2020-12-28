package com.github.jmlb23.mediumclone

import androidx.compose.runtime.*

@Composable
fun <S,A> useStore(store: Store<S,A>): State<S>{
    val (state, setState) = remember {  mutableStateOf(store.getState()) }

    onCommit(callback = {
        val subscription = store.subscribe(setState)
        onDispose {
            store.dispose(subscription)
        }
    })

    return derivedStateOf { state }
}