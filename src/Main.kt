import kotlin.properties.ReadOnlyProperty
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

fun main() {
    val massage="weak reference get() fun. returns null"

    // Example with String value
    val stringValue = WeakRefKeeper("String in weak reference")
    stringValue.value?.also { println(it) } ?: massage.also { println(it) }


    // Example with Integer value
    val intValue = WeakRefKeeper(massage.length)
    intValue.value?.also { println(it) } ?: massage.also { print(it) }
}

class WeakRefKeeper<T>(value: T?) {
    val value: T? by putInWeakRef(value)
}

fun <R,T> putInWeakRef(value:T?): WeakDelegation<R,T> = WeakDelegation(WeakReference(value))

class WeakDelegation<R, T>(
    private val value: WeakReference<T?>
)  {

    operator fun getValue(thisRef: R, property: KProperty<*>): T? {
        return value.get()
    }

}

