import kotlinx.cinterop.*
import kotlin.test.*

import simd_wrapper.*

import platform.Accelerate.*

fun test_dist() {
    var v1 = vectorOf(-1f, 0f, 0f, -7f)
    var v2 = vectorOf(1f, 4f, 3f, 7f);
    val len = my_simd_distance(v1, v2)
    println(len)
}

fun test_Accelerate() {
    var v1 = vectorOf(1f, 2f, 4f, 9f)
    printVFloat(vsqrtf(v1))
}

class Box<T>(t: T) {
    var value = t
}


fun test_OOB() {
    val f = vectorOf(1f, 3.162f, 10f, 31f)
    println("getByte(15) is OK: ${f.getByte(15)}")
    var result = "PASSED"
    try {
        println("getFloat(4) should fail: ${f.getFloat(4)}")
        result = "FAILED"
    } catch (e: IndexOutOfBoundsException) {}

    try {
        println(f.getInt(-1))
        result = "FAILED"
    } catch (e: IndexOutOfBoundsException) {}

    try {
        println(f.getFloat(4))
        result = "FAILED"
    } catch (e: IndexOutOfBoundsException) {}
    println("Exception test $result")
}

fun test_equals() {
    var v1 = vectorOf(-1f, 0f, 0f, -7f)
    var v2 = vectorOf(1f, 4f, 3f, 7f)
    println("v1.equals(v2) = ${v1.equals(v2)}")
    v1 = v2
    println("Now v1.equals(v2) = ${v1.equals(v2)}")
    val d = 42f
    println("v1.equals(d) = ${v1.equals(d)}")

    val x = (v1 == v2)  // type of x is <4 x i1>
//    println("x = $x") // <4 x i1> not supported yet
}


fun main() {

    val v = vectorOf(42f, 1f, 2f, 3f)
    val box: Box<NativeVector> = Box<NativeVector>(v)

    val f2 = vectorOf(1f, 3.162f, 10f, 31f)
    println(f2.getFloat(1))
    println(f2.getInt(0))
    println(f2.getByte(0))
    println(f2.getUByte(1))
    println(f2.getUByte(2))
    println(f2.getByte(3))

    println("vlog10f($f2) = ${vlog10f(f2)}")

    test_equals()

    test_Accelerate()

    test_OOB()
}
