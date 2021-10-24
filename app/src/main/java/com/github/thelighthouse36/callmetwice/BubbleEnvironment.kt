package com.github.thelighthouse36.callmetwice


var bubbleEnvironment: ArrayList<Bubble> = arrayListOf()


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

fun toTime(input: String): Int {
    val values = input.split(":");
    return Integer.parseInt(values[0]) * 60 + Integer.parseInt(values[1])
}

class Bubble(val number: String, var life: Int) {
    override fun toString(): String {
        return "Bubble(number='$number', life=$life)"
    }
}


