package androidx.lifecycle

import android.app.Activity
import androidx.annotation.VisibleForTesting

/**
 * Put in this package because of visibility restrictions of [EmptyActivityLifecycleCallbacks], which allows us to
 * override only the required methods, decreasing the noise. If more methods are required, override here.
 */
internal class ActivityLifecycleListener : EmptyActivityLifecycleCallbacks() {

    val counter = mutableMapOf<String, Int>()

    override fun onActivityStarted(activity: Activity?) = super.onActivityStarted(activity).also {
        activity?.javaClass?.simpleName?.let { onActivityStarted(it) }
    }

    override fun onActivityStopped(activity: Activity?) = super.onActivityStopped(activity).also {
        activity?.javaClass?.simpleName?.let { onActivityStopped(it) }
    }

    @VisibleForTesting
    fun onActivityStarted(simpleName: String) = counter[simpleName].let {
        counter.put(simpleName, it?.plus(1) ?: 1)
    }

    @VisibleForTesting
    fun onActivityStopped(simpleName: String) = counter[simpleName].let {
        counter.put(simpleName, it?.run { return@run if (this == 0) 0 else minus(1) } ?: 0)
    }
}
