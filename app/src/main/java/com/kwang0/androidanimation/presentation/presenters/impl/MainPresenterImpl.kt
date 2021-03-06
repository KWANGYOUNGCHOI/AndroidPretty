package com.kwang0.androidanimation.presentation.presenters.impl

import com.kwang0.androidanimation.presentation.presenters.MainContract

class MainPresenterImpl(var view: MainContract.View): MainContract.Presenter {
    override fun resume() {
        view.refresh()
    }
}
