package androidx.lifecycle

import android.app.Activity
import android.app.Application
import androidx.annotation.VisibleForTesting

object LifecycleCallbacks {



    private val activityListener: ActivityLifecycleListener by lazy { ActivityLifecycleListener() }

    fun activityListener(): Application.ActivityLifecycleCallbacks = activityListener

    fun isActivityShown(clazz: Class<out Activity>): Boolean = isActivityShown(clazz.simpleName)

    @VisibleForTesting
    internal fun isActivityShown(simpleName: String): Boolean =
        listenerCounter()[simpleName].run { this != null && this > 0 }

    @VisibleForTesting
    internal fun listenerCounter(): Map<String, Int> = activityListener.counter
}
