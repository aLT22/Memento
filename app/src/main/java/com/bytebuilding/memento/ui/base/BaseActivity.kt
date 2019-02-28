package com.bytebuilding.memento.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bytebuilding.memento.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass


abstract class BaseActivity<V : ViewDataBinding, out VM : BaseViewModel, VS : BaseViewState>(
    clazz: KClass<out VM>
) : AppCompatActivity(), CoroutineScope {

    protected val mJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mJob

    protected lateinit var mBinding: V
    protected val mViewModel: VM by viewModelByClass(clazz)
    protected lateinit var mViewState: VS

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun viewState(): VS

    abstract fun initViews()
    abstract fun initListeners()
    abstract fun observeChanges()
    abstract fun removeListeners()

    abstract fun render(viewState: VS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewState = viewState()

        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mBinding.setLifecycleOwner(this)
        mBinding.setVariable(BR.vm, mViewModel)

        initViews()
        render(mViewState)
    }

    override fun onStart() {
        super.onStart()

        initListeners()
        observeChanges()
    }

    override fun onStop() {
        mJob.cancelChildren()
        removeListeners()

        super.onStop()
    }

}