package com.github.thelighthouse36.callmetwice


val bubbleEnvironment: ArrayList<Bubble> = arrayListOf()
val sampleNumbers = arrayListOf("111-111-1111", "111-111-1112", "111-111-1113")
val sampleLives = arrayListOf("5:31", "6:66", "1:23")
//var strings = mutableListOf("Apple", "Banana", "Kumquat")

fun addBubble(bubble: Bubble) {
    bubbleEnvironment.add(bubble);
}

fun addBubble(number: String, life: Int) {
    addBubble(Bubble(number, life))
}

//fun addString(string: String) {
//    strings.add(string)}

fun bubbleEnvContainsNumber(number: String): Boolean {
    for (bubble in bubbleEnvironment) {
        if(bubble.number == number) { return true }
    }
    return false
}
class Bubble(val number: String, var life: Int) {
    override fun toString(): String {
        return "Bubble(number='$number', life=$life)"
    }
}


