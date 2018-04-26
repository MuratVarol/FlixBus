package io.varol.flixbus.app.base

import java.lang.ref.WeakReference

/**
 * Created by varol on 26.4.2018.
 */

open class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    private var weakRef: WeakReference<V>? = null

    val view: V?
        get() = weakRef?.get()

    private val isViewAttached: Boolean
        get() = weakRef != null && weakRef!!.get() != null

    /**
     * Attach presenter with weak ref. to help garbage collector clean unused weak referenced presenter.
     * It helps to prevent memory leaks
     */
    override fun attachView(view: V) {
        if (!isViewAttached){
            weakRef= WeakReference(view)
            view.initPresenter(this)
        }
    }

    /**
     * This method should be called activity's onDestroy method
     */
    override fun detachView() {
        weakRef?.clear()
        weakRef = null
    }



}