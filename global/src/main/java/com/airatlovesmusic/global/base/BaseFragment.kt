package com.airatlovesmusic.global.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.airatlovesmusic.global.R
import com.airatlovesmusic.global.di.DI
import com.airatlovesmusic.global.extensions.objectScopeName
import com.google.android.material.snackbar.Snackbar
import toothpick.Scope
import toothpick.Toothpick
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


private const val STATE_SCOPE_NAME = "state_scope_name"

abstract class BaseFragment : Fragment() {
    abstract val layoutRes: Int

    private var instanceStateSaved: Boolean = false

    protected open val parentScopeName: String by lazy {
        (parentFragment as? BaseFragment)?.fragmentScopeName
            ?: DI.APP_SCOPE
    }

    lateinit var fragmentScopeName: String
    lateinit var scope: Scope
        private set

    protected open fun installModules(scope: Scope) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentScopeName = savedInstanceState?.getString(STATE_SCOPE_NAME) ?: objectScopeName()

        if (Toothpick.isScopeOpen(fragmentScopeName)) {
            scope = Toothpick.openScope(fragmentScopeName)
        } else {
            scope = Toothpick.openScopes(parentScopeName, fragmentScopeName)
            installModules(scope)
        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutRes, container, false)

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
        outState.putString(STATE_SCOPE_NAME, fragmentScopeName)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (needCloseScope()) {
            Toothpick.closeScope(scope.name)
        }
    }

    fun isRealRemoving(): Boolean =
        (isRemoving && !instanceStateSaved) ||
                ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)

    private fun needCloseScope(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }

    inline fun <reified T> scope() =
        object : ReadWriteProperty<BaseFragment, T> {
            private var value: Any? = null
            override fun setValue(thisRef: BaseFragment, property: KProperty<*>, value: T) {
                this.value = value
            }
            override fun getValue(thisRef: BaseFragment, property: KProperty<*>): T {
                if (value == null) {
                    val instance = thisRef.scope.getInstance(T::class.java)
                    check(instance != null && instance is T) { "Property is null or has different class type" }
                    value = instance
                }
                return value as T
            }
        }

    infix fun <U> LiveData<U>.bindTo(f: (U) -> Unit) {
        observe(this@BaseFragment, Observer { it?.let(f)  })
    }

    open fun showError(message: String?) {
        view?.let {
            if(!message.isNullOrEmpty()) {
                Snackbar.make(it, message ?: "", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.coralRed))
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    .apply {
                        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                            ?.let { tv -> tv.maxLines = 5 }
                    }
                    .show()
            }
        }
    }

    fun showSuccess(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.successGreen))
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                .show()
        }
    }

    open fun onBackPressed() {}
}