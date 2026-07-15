
/*

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions
https://www.w3schools.com/js/js_functions.asp

*/

let dado1 = 1;

function f1() {
  console.log(dado1);
}

function f1WithParam(data) {
  if (data > 24) {
    console.log("Erro");
    return;
  }

  console.log(data)
  data++;

  return data;
}

function inc(base, inc = 1) {
  return base + inc 
}

let event = {};

function check(e) {
  
}

check(event)
// inc(3);
// inc(12);

function showUser(nome, idade) {
  console.log(nome + " , " + idade);
}

showUser('Joao', 42);

let user = {
  name: 'Manel',
  age: 42
}
showUser(user.name, user.age)