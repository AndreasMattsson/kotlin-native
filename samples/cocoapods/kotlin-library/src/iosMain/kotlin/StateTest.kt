import kotlin.random.Random

private val _randomFileLevelVal = Random.Default.nextInt()
private val _randomFileLevelLazy by lazy { Random.Default.nextInt() }

open class StateTest {
    init {
        println("StateTest init")
    }

    val randomInstanceVal = Random.Default.nextInt()

    val randomInstanceLazy by lazy {
        Random.Default.nextInt()
    }

    val randomFileLevelVal get() =  _randomFileLevelVal

    val randomFileLevelLazy get() =  _randomFileLevelLazy

    object InheritingSingleton : StateTest() {
        init {
            println("StateTest.InheritingSingleton init")
        }
    }

    object IndependentSingleton {
        init {
            println("StateTest.IndependentSingleton init")
        }

        val randomSingletonLazy by lazy { Random.Default.nextInt() }
        val randomSingletonSimple = Random.Default.nextInt()
    }

}