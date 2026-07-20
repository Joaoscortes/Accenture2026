class MyTable {
  id = '';
  headers = []; // ["name","age"]
  data = [];
  constructor(id, headers) {
    // validate if id exist
    this.id = id;
    this.headers = headers;
  }

  init() {
    let headRow = "<tr>";
    for (const header of this.headers) {
      headRow += `<th>${header}</th>`
    }
    headRow = headRow + "</tr>";

    document.getElementById(this.id).innerHTML = `
          <table>
              <thead id="${this.id}-head">
                ${headRow}
              </thead>
              <tbody id="${this.id}-body">
              </tbody>
            </table>
          `

    if (this.data.length > 0) {
      this.data.forEach((dataCell) => {
        this.addBodyRow(dataCell);
      })
    }
  }

  addBodyRow(value) { // user = { name: "Joao", age: 1}
    let rowToAdd = "<tr>"
    for (const header of this.headers) { // 0:"name" 1:"age"
      rowToAdd += `<td>${value[header]}</td>`
    }
    rowToAdd += "</tr>"
    document.getElementById(`${this.id}-body`).insertAdjacentHTML("beforeend", rowToAdd);
  }

  pushToData(value) {
    this.data.push(value);
  }
}
