package io.varol.flixbus.app.base

/**
 * Created by varol on 26.4.2018.
 */

interface BaseContract {

    interface View {

        fun initView()
        fun initVariables()
        fun initPresenter(presenter: BasePresenter<*>)

        fun showLoadingDialog()
        fun hideLoadingDialog()

    }

    interface Presenter<in V : View> {

        fun attachView(view: V)

        fun detachView()

    }

}