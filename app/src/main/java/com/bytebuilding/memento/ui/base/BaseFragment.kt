package com.bytebuilding.memento.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.bytebuilding.memento.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

/**
 * Created by Turkin A. on 06/01/2019.
 */
abstract class BaseFragment<V : ViewDataBinding, out VM : ViewModel>(
    clazz: KClass<VM>
) : Fragment(), CoroutineScope {

    protected val mJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mJob

    protected lateinit var mBinding: V
    protected val mViewModel: VM by viewModelByClass(clazz)

    @LayoutRes
    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mBinding.setLifecycleOwner(this)
        mBinding.setVariable(BR.vm, mViewModel)

        return mBinding.root
    }

    override fun onDestroyView() {
        mJob.cancelChildren()

        super.onDestroyView()
    }
}