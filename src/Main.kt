import kotlin.properties.ReadOnlyProperty
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

fun main() {
    val massage="weak reference get() fun. returns null"

    // Example with String value
    val stringValue = WeakRefKeeper("String in Weak Reference")
    stringValue.value.get()?.also { println(it) } ?: massage.also { print(it) }

    // Example with Integer value
    val intValue = WeakRefKeeper(massage.length)
    intValue.value.get()?.also { println(it) } ?: massage.also { print(it) }
}

class WeakRefKeeper<T>(value: T) {
    val value: WeakReference<T> by WeakDelegation<WeakRefKeeper<T>, WeakReference<T>>(WeakReference(value))
}

class WeakDelegation<R, T>(
    private val value: T
) : ReadOnlyProperty<R, T> {

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return value
    }

}

