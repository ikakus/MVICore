package com.badoo.mvicore.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import io.reactivex.Observable
import io.reactivex.Observable.just

open class ReducerFeature<Wish : Any, State : Any>(
    initialState: State,
    reducer: Reducer<State, Wish>,
    bootstrapper: Bootstrapper<Wish>? = null
) : DefaultFeature<Wish, Wish, Wish, State>(
    initialState = initialState,
    bootstrapper = bootstrapper,
    wishToAction = { wish -> wish },
    actor = BypassActor(),
    reducer = reducer
) {
    class BypassActor<in State : Any, Wish : Any> : Actor<State, Wish, Wish> {
        override fun invoke(state: State, wish: Wish): Observable<Wish> =
            just(wish)
    }
}