import kotlin.properties.ReadOnlyProperty
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

fun main() {
    val massage="weak reference get() fun. returns null"

    // Example with String value
    var stringValue = WeakRefKeeper("String in weak reference")
    stringValue=WeakRefKeeper("new value")
    stringValue.value?.also { println(it) } ?: massage.also { println(it) }


    // Example with Integer value
    val intValue = WeakRefKeeper(massage.length)
    intValue.value?.also { println(it) } ?: massage.also { print(it) }
}

class WeakRefKeeper<T>(value: T?) {
    var value: T? by putInWeakRef(value)
}

fun <R,T> putInWeakRef(value:T?): WeakDelegation<R,T> = WeakDelegation(WeakReference(value))

class WeakDelegation<R, T>(
    private var value: WeakReference<T?>
)  {

    operator fun getValue(thisRef: R, property: KProperty<*>): T? {
        return value.get()
    }

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T?) {
        this.value = WeakReference(value)

    }
}

