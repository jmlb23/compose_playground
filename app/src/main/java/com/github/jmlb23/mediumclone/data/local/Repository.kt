package com.github.jmlb23.mediumclone.data.local

import kotlinx.coroutines.flow.Flow

interface Repository<K, V, O> {
    suspend fun add(v: V): O
    suspend fun get(k: K): V
    suspend fun remove(k: K): O
    suspend fun filter(predicate: (V) -> Boolean): Flow<List<V>>
    suspend fun replace(k: K, new: V): O
}

fun <K, V, O> createRepo(
    adder: suspend (V) -> O,
    getter: suspend (K) -> V,
    remover: suspend (K) -> O,
    filterer: suspend ((V) -> Boolean) -> Flow<List<V>>,
    replacer: suspend (K, V) -> O
) = object : Repository<K, V, O> {
    override suspend fun add(v: V): O =
        adder(v)

    override suspend fun get(k: K): V =
        getter(k)

    override suspend fun remove(k: K): O = remover(k)

    override suspend fun filter(predicate: (V) -> Boolean): Flow<List<V>> =
        filterer(predicate)

    override suspend fun replace(k: K, new: V): O =
        replacer(k, new)
}