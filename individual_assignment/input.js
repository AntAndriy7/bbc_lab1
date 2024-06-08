// Оголошення змінних та функції
let name = "Ivan";
const age = 30.5;

/*
* let name = "Ivan";
* const age = 30.5;
*/

function hello(person) {
    console.log("Hello, " + person + "!");
}

hello(name);

// Умовний оператор
if (age >= 18) {
    console.log(name + " is an adult.");
} else {
    console.log(name + " is a minor.");
}

// Цикл for
for (let i = 0; i < 5; i++) {
    console.log("Iteration " + i);
}

// Об'єкт
let person = {
    name: "Bob",
    age: 20.5,
    hello: function() {
        console.log("Hi, my name is " + this.name);
    }
};

// Массив та робота з ним
let numbers = [1, 2, 3, 4, 5];
numbers.forEach(function(number) {
    console.log("Number: " + number);
});