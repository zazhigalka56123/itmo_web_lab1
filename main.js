class Database {
    constructor(tableId) {
        this.table = document.getElementById(tableId);
    }

    loadFromLocalStorage() {
        const db = JSON.parse(localStorage.getItem('table'));
        if (db) {
            db.forEach(rowData => this.addRow(rowData));
        }
    }

    addRow(data) {
        const newRow = this.table.insertRow(1);
        data.forEach(content => {
            const cell = newRow.insertCell();
            cell.textContent = content;
        });
    }

    clear() {
        while (this.table.rows.length > 1) {
            this.table.deleteRow(1);
        }
    }

    updateLocalStorage() {
        localStorage.setItem('table', JSON.stringify(this.tableToJson()));
    }

    tableToJson() {
        const data = [];
        for (let i = 1; i < this.table.rows.length; i++) {
            const rowData = Array.from(this.table.rows[i].cells).map(cell => cell.textContent);
            data.push(rowData);
        }
        return data;
    }
}

class FormValidator {
    static isValid(values) {
        if (values.x === undefined || values.y === undefined || values.r === undefined) {
            alert("Укажите все значения");
            return false;
        }
        if (!(values.x >= -3 && values.x <= 3)) {
            alert("Введите корректное значение X");
            return false;
        }
        return true;
    }
}

class Main {
    constructor() {
        this.tableManager = new Database('table');
    }

    init() {
        window.addEventListener('load', () => this.tableManager.loadFromLocalStorage());
        document.getElementById('form').addEventListener('submit', event => this.handleSubmit(event));
    }

    handleSubmit(event) {
        event.preventDefault();
        let mainForm = document.getElementById('form');
        const formData = new FormData(mainForm);
        const values = Object.fromEntries(formData);

        if (FormValidator.isValid(values)) {
            this.startConnection(formData);
        }
    }
    startConnection(data) {
        console.log("data")
        console.log(data)
        fetch('/fcgi-bin/fcgi.jar?' + new URLSearchParams(data).toString(), { method: "GET" })
            .then(response => response.text())
            .then(answer => this.handleResponse(answer))
            .catch(error => console.error('Error:', error));
    }
    handleResponse(answer) {
        const answ = JSON.parse(answer);

        if (answ.code === "200") {
            if (this.tableManager.table.rows.length >= 10) {
                this.tableManager.clear();
            }
            const rowData = [
                answ.result === "true" ? "YES" : "NO",
                answ.x, answ.y, answ.r, answ.responseTime, answ.workTime
            ];
            this.tableManager.addRow(rowData);
            this.tableManager.updateLocalStorage();
        } else {
            alert(answ.result);
        }
    }
}

const app = new Main();
app.init();
