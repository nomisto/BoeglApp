import sqlite3
from flask import Flask, request, jsonify
import json

app = Flask(__name__)

dbName = "qwer.db"


@app.route('/tools', methods=['POST'])
def insert_tool():
    tool = request.get_json()
    with sqlite3.connect(dbName) as con:
        con.row_factory = sqlite3.Row
        con.execute("PRAGMA foreign_keys = 1")
        con.execute(f"INSERT INTO Tools (NAME) VALUES ('{tool['name']}')")
        con.commit()


@app.route('/tools', methods=['GET'])
def get_all_tools():
    with sqlite3.connect(dbName) as con:
        con.row_factory = sqlite3.Row
        con.execute("PRAGMA foreign_keys = 1")
        return json.dumps([dict(ix) for ix in con.execute("SELECT * FROM Tools")])


@app.route('/history', methods=['POST'])
def insert_history():
    history = request.get_json()
    with sqlite3.connect(dbName) as con:
        con.row_factory = sqlite3.Row
        con.execute("PRAGMA foreign_keys = 1")
        con.execute(f"INSERT INTO History (TOOL_ID, EMPLOYEE, FROM_DATE, TO_DATE) VALUES (" +
                    f"{history['toolid']}, " +
                    f"'{history['employee']}', " +
                    f"{history['from_date']}, " +
                    f"{history['to_date']}" +
                    ")")
        con.commit()


@app.route('/history/<int:tool_id>', methods=['GET'])
def get_histories_by_tool_id(tool_id):
    with sqlite3.connect(dbName) as con:
        con.row_factory = sqlite3.Row
        con.execute("PRAGMA foreign_keys = 1")
        return json.dumps([dict(ix) for ix in con.execute(f"SELECT * FROM History WHERE TOOL_ID = {tool_id}")])


def setup():
    with sqlite3.connect(dbName) as con:
        con.row_factory = sqlite3.Row
        con.execute("PRAGMA foreign_keys = 1")
        con.execute('''CREATE TABLE Tools(
                 ID INTEGER PRIMARY KEY AUTOINCREMENT,
                 NAME TEXT NOT NULL
                 );''')

        con.execute('''CREATE TABLE History(
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    TOOL_ID INTEGER NOT NULL,
                    EMPLOYEE TEXT NOT NULL,
                    FROM_DATE DATETIME NOT NULL,
                    TO_DATE DATETIME NOT NULL,
                    FOREIGN KEY (TOOL_ID) REFERENCES Tools(ID)
                );''')


if __name__ == '__main__':
    app.run(debug=True)
