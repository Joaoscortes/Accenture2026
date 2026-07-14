/***************************************************************************
 *
 *   SYNTAX EXAMPLES
 *
 ***************************************************************************/
let users;
users = ["Zé Carlos", "Zé Manel", "Samuel Matarruano"];
let users2 = [];
const users3 = [];
// const users4;  // SyntaxError: Missing initializer in const declaration

// Copy array
const arrayInitPrim = [1, 2, 3];
const arrayInitComp = [1, {a1: 1}, 3];

// const copyArrayInitPrim = arrayInitPrim; // Cuidado
// const copyArrayInitComp = arrayInitComp; // Cuidado

// const copyArrayInitPrim = arrayInitPrim.slice();
// const copyArrayInitComp = arrayInitComp.slice(); // Cuidado

// const copyArrayInitPrim = Object.assign([], arrayInitPrim);
// const copyArrayInitComp = Object.assign([], arrayInitComp); // Cuidado

// const copyArrayInitPrim = [...arrayInitPrim];
// const copyArrayInitComp = [...arrayInitComp]; // Cuidado

// const copyArrayInitPrim = JSON.parse(JSON.stringify(arrayInitPrim));
// const copyArrayInitComp = JSON.parse(JSON.stringify(arrayInitComp));

// copyArrayInitPrim.push(4);
// console.log("arrayInitPrim     :", arrayInitPrim);
// console.log("copyArrayInitPrim :", copyArrayInitPrim);

// copyArrayInitComp.push(4);
// copyArrayInitComp[1].a1 = 5;
// console.log("arrayInitComp     :", arrayInitComp);
// console.log("copyArrayInitComp :", copyArrayInitComp);

/***************************************************************************
 *
 *   LOOPS ARRAY
 *
 ***************************************************************************/

/*
 *   for
 */
// let usersCopy = [...users];
// for (let index = 0; index < usersCopy.length; index++) {
//     const element = usersCopy[index];
//     console.log(element);
// }

/*
 *   for/in -> JSON
 */
// let usersCopy = [...users];
// for (const key in usersCopy) {
//     if (usersCopy.hasOwnProperty(key)) {
//         const element = usersCopy[key];
//         console.log(key, element);
//     }
// }

/*
 *   for/of -> Array
 */
// let usersCopy = [...users];
// for (const iterator of usersCopy) {
//     console.log(iterator);
// }

/*
 *   while
 */
// let usersCopy = [...users];
// let index = 0;
// while (index < usersCopy.length) {
//     console.log(usersCopy[index]);
//     index++;
// }

/***************************************************************************
 *
 *   ARRAY METHODS
 *
 ***************************************************************************/

/*
 *   To string
 */
// console.log("toString :", users.toString());
// console.log("join     :", users.join(","));

/*
 *   Add to array (push, unshift)
 */
// push() -> End
// users2.push("Zé Carlos");
// users2.push("Zé Manel");
// const returnOfPush = users2.push("Samuel Matarruano");
// console.log("After push", users2, "returnOfPush", returnOfPush);

// unshift() -> Index 0
// users2.unshift("Zé Carlos");
// users2.unshift("Zé Manel");
// const returnOfUnshift = users2.unshift("Samuel Matarruano");
// console.log("After unshift", users2, "returnOfUnshift", returnOfUnshift);

// by index
// users2[users2.length] = "Zé Carlos";

/*
 *   Remove from array (pop, shift)
 */
// pop() -> last element
// let usersCopy = [...users];
// let user = usersCopy.pop();
// // usersCopy.pop();
// console.log("After pop", usersCopy, "Pop return value : ", user);

// shift() -> first element
// let usersCopy = [...users];
// let user = usersCopy.shift();
// // usersCopy.shift();
// console.log("After shift", usersCopy, "Shift return value : ", user);

/*
 *   Splice method
 */
// let usersCopy = [...users];
// let user = usersCopy.splice(0, 1);
// // usersCopy.splice(0, 0, "Gata");
// console.log("After splice", usersCopy, "Splice return value : ", );

/*
 *   Slice method
 */
// let usersCopy = [...users];
// let user = usersCopy.slice(0, 1);
// console.log(user);

/*
 *   Concat method
 */
// let usersCopy = [...users];
// let newArray = ["Gata"];
// let result = newArray.concat(usersCopy);
// console.log(result);

/*
 *   Sort method
 */
// let usersCopy = [...users];
// console.log(usersCopy);
// usersCopy.sort();
// console.log(usersCopy);

// let numbers = [12, 2019, 1, 50, 2020, 14];
// numbers.sort(function(a, b) { return a - b });
// console.log(numbers);

/*
 *   Reverse method
 */
// let usersCopy = [...users];
// // usersCopy.sort();
// usersCopy.reverse();
// console.log(usersCopy);

/*
 *   IndexOf method
 */
// let usersCopy = [...users];
// let userId = usersCopy.indexOf("Zé Carlos");
// console.log(userId);

/*
 *   forEach method
 */
// let usersCopy = [...users];
// usersCopy.forEach(myFunction);

// function myFunction(value, index) {
//     console.log(index, value);
// }
// usersCopy.forEach((item, index) => {
//     item = item.toUpperCase();
//     console.log(index, item);
// });
// console.log(usersCopy);

/*
 *   Map method
 */
// let usersCopy = [...users];
// let result = usersCopy.map((element) => element.toLocaleUpperCase());
// console.log(result);
// console.log(usersCopy);

/*
 *   Filter method
 */
// let usersCopy = [...users];
// let result = usersCopy.filter((element) => element.length >= 9);
// console.log(result);

/*
 *   Every method
 */
// let usersCopy = [...users];
// let result = usersCopy.every((element) => element.length > 9);
// console.log(result);

/*
 *   Find method
 */
// let usersCopy = [...users];
// let result = usersCopy.find((element) => element.length >= 9);
// console.log(result);

/*
 *   FindIndex method
 */
// let usersCopy = [...users];
// let result = usersCopy.findIndex((element) => element.length > 9);
// console.log(result);
