package androidx.lifecycle

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class LifecycleCallbacksTest {

    @Mock internal lateinit var mockCallbacks: ActivityLifecycleListener

    private val className = "MyActivity"

    private lateinit var lifecycleCallbacks: LifecycleCallbacks

    @Before
    fun setUp() {
        lifecycleCallbacks = spy(LifecycleCallbacks)
    }

    @Test
    fun `returns the Activity Listener`() {
        doReturn(mockCallbacks).whenever(lifecycleCallbacks).activityListener()

        val actualCallbacks = lifecycleCallbacks.activityListener()

        assertNotNull(actualCallbacks)
        assertEquals(mockCallbacks, actualCallbacks)
    }

    @Test
    fun `reports that an Activity is shown if the counter is larger than 0`() {
        val counter = mapOf(className to Random.nextInt(1, Int.MAX_VALUE))
        doReturn(counter).whenever(lifecycleCallbacks).listenerCounter()

        val isShown = lifecycleCallbacks.isActivityShown(className)

        assertTrue(isShown)
    }

    @Test
    fun `reports that an Activity is not shown if the counter is 0`() {
        doReturn(mapOf(className to 0)).whenever(lifecycleCallbacks).listenerCounter()

        val isShown = lifecycleCallbacks.isActivityShown(className)

        assertFalse(isShown)
    }

    @Test
    fun `reports that an Activity is not shown if the counter returns null for the name`() {
        doReturn(emptyMap<String, Int>()).whenever(lifecycleCallbacks).listenerCounter()

        val isShown = lifecycleCallbacks.isActivityShown(className)

        assertFalse(isShown)
    }

    @Test
    fun `returns the listener counter`() {
        val listenerCounter = lifecycleCallbacks.listenerCounter()

        assertNotNull(listenerCounter)
        assertEquals((lifecycleCallbacks.activityListener() as ActivityLifecycleListener).counter, listenerCounter)
    }
}
