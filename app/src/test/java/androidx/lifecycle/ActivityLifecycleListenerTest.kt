package androidx.lifecycle

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class ActivityLifecycleListenerTest {

    private val className = "className"
    private val otherName = "otherName"
    private val yetAnotherName = "yetAnotherName"

    private lateinit var activityLifecycleListener: ActivityLifecycleListener

    @Before
    fun setUp() {
        activityLifecycleListener = ActivityLifecycleListener()
    }

    @Test
    fun `onActivityStarted called with null puts nothing into the counter`() {
        activityLifecycleListener.onActivityStarted(null)
        assertTrue(activityLifecycleListener.counter.isEmpty())
    }

    @Test
    fun `onActivityStarted sets count to 1 for a name first encountered`() {
        activityLifecycleListener.onActivityStarted(className)
        assertEquals(1, activityLifecycleListener.counter[className])
    }

    @Test
    fun `onActivityStarted sets count to 3 for a name three times encountered`() {
        for (i in 1..3) {
            activityLifecycleListener.onActivityStarted(className)
        }
        assertEquals(3, activityLifecycleListener.counter[className])
    }

    @Test
    fun `onActivityStarted sets correct count for a mix of started class names`() {
        activityLifecycleListener.onActivityStarted(className)
        activityLifecycleListener.onActivityStarted(otherName)
        activityLifecycleListener.onActivityStarted(className)
        activityLifecycleListener.onActivityStarted(yetAnotherName)
        activityLifecycleListener.onActivityStarted(otherName)
        activityLifecycleListener.onActivityStarted(className)

        assertEquals(3, activityLifecycleListener.counter[className])
        assertEquals(2, activityLifecycleListener.counter[otherName])
        assertEquals(1, activityLifecycleListener.counter[yetAnotherName])
    }

    @Test
    fun `onActivityStopped called with null puts nothing into the counter`() {
        activityLifecycleListener.onActivityStopped(null)
        assertTrue(activityLifecycleListener.counter.isEmpty())
    }

    @Test
    fun `onActivityStopped sets count to 0 for a name first encountered`() {
        activityLifecycleListener.onActivityStopped(className)
        assertEquals(0, activityLifecycleListener.counter[className])
    }

    @Test
    fun `onActivityStopped sets correct count for a mix of stopped class names`() {
        activityLifecycleListener.onActivityStopped(className)
        activityLifecycleListener.onActivityStopped(otherName)
        activityLifecycleListener.onActivityStopped(className)
        activityLifecycleListener.onActivityStopped(yetAnotherName)
        activityLifecycleListener.onActivityStopped(otherName)
        activityLifecycleListener.onActivityStopped(className)

        assertEquals(0, activityLifecycleListener.counter[className])
        assertEquals(0, activityLifecycleListener.counter[otherName])
        assertEquals(0, activityLifecycleListener.counter[yetAnotherName])
    }

    @Test
    fun `onActivityStarted sets correct count for a mix of started and stopped class names`() {
        activityLifecycleListener.onActivityStarted(className)
        activityLifecycleListener.onActivityStopped(className)
        activityLifecycleListener.onActivityStarted(className)

        activityLifecycleListener.onActivityStarted(otherName)
        activityLifecycleListener.onActivityStarted(otherName)
        activityLifecycleListener.onActivityStarted(otherName)
        activityLifecycleListener.onActivityStopped(otherName)

        activityLifecycleListener.onActivityStarted(yetAnotherName)
        activityLifecycleListener.onActivityStarted(yetAnotherName)
        activityLifecycleListener.onActivityStopped(yetAnotherName)
        activityLifecycleListener.onActivityStarted(yetAnotherName)
        activityLifecycleListener.onActivityStarted(yetAnotherName)

        assertEquals(1, activityLifecycleListener.counter[className])
        assertEquals(2, activityLifecycleListener.counter[otherName])
        assertEquals(3, activityLifecycleListener.counter[yetAnotherName])
    }
}