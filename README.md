# androidApp_task_by_AppIsKey
 
**Android app task app is developed as per requirments.**
1) Koin
2) MVVM archtecture (viewmodel and repository classes)
3) Live data for observing live changes
4) Fragment naviagtion
5) extentions
6) oKKHTTP for api call
7) decent design 


**Programm Task)**
**solution->**
fun countWords(input: String): Map<String, Int> {
    // Initialize a mutable map to store word counts
    val wordCounts = mutableMapOf<String, Int>()

    // Use regular expressions to tokenize the input and ignore punctuation
    val words = input.split(Regex("\\W+"))

    // Iterate through the words, normalize, and count them
    for (word in words) {
        val normalizedWord = word.toLowerCase()
        // Update the count in the dictionary
        wordCounts[normalizedWord] = wordCounts.getOrDefault(normalizedWord, 0) + 1
    }

    return wordCounts
}

fun main() {
    //val input = "The cat and the horse hat"   // result= ["the": 2, "cat": 1, "and": 1,"hat": 1].
    val inout="My name is Rasool"    //  result= ["my": 1, "name": 1, "is": 1,"Rasool": 1].
    val result = countWords(input)
    println(result)   
}
