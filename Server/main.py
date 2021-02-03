import sqlite3
from flask import Flask, request, jsonify
import json

app = Flask(__name__)

dbName = "test.db"


def open_connection():
    con = sqlite3.connect(dbName)
    con.row_factory = sqlite3.Row
    con.execute("PRAGMA foreign_keys = 1")
    return con


@app.route('/tools', methods=['POST'])
def insert_tool():
    tool = request.get_json()
    print(tool)
    with open_connection() as con:
        con.execute(f"INSERT INTO Tools (NAME) VALUES ('{tool['name']}')")
        con.commit()


@app.route('/tools', methods=['PUT'])
def update_tool():
    tool = request.get_json()
    with open_connection() as con:
        con.execute(f"UPDATE Tools SET NAME = '{tool['name']}' WHERE id = {tool['id']}")
        con.commit()


@app.route('/tools', methods=['DELETE'])
def delete_tool():
    tool = request.get_json()
    with open_connection() as con:
        con.execute(f"DELETE FROM Tools WHERE ID = {tool['id']}")
        con.commit()


@app.route('/tools', methods=['GET'])
def get_all_tools():
    with open_connection() as con:
        return json.dumps([dict(ix) for ix in con.execute("SELECT * FROM Tools")])


@app.route('/history', methods=['POST'])
def insert_history():
    history = request.get_json()
    with open_connection() as con:
        con.execute(f"INSERT INTO History (TOOL_ID, EMPLOYEE, FROM_DATE, TO_DATE) VALUES (" +
                    f"{history['toolid']}, " +
                    f"'{history['employee']}', " +
                    f"{history['from_date']}, " +
                    f"{history['to_date']}" +
                    ")")
        con.commit()


@app.route('/history', methods=['PUT'])
def update_history():
    history = request.get_json()
    with open_connection() as con:
        con.execute(f"UPDATE History SET "
                    f"TOOL_ID = {history['toolid']}, " +
                    f"EMPLOYEE = '{history['employee']}', " +
                    f"FROM_DATE = {history['from_date']}, " +
                    f"TO_DATE = {history['to_date']} " +
                    f"WHERE id = {history['id']}")
        con.commit()


@app.route('/history', methods=['DELETE'])
def delete_history():
    history = request.get_json()
    with open_connection() as con:
        con.execute(f"DELETE From History WHERE id = {history['id']}")
        con.commit()


@app.route('/history/<int:tool_id>', methods=['GET'])
def get_histories_by_tool_id(tool_id):
    with open_connection() as con:
        return json.dumps([dict(ix) for ix in con.execute(f"SELECT * FROM History WHERE TOOL_ID = {tool_id}")])


def setup():
    with open_connection() as con:
        con.execute('''CREATE TABLE IF NOT EXISTS Tools(
                 ID INTEGER PRIMARY KEY AUTOINCREMENT,
                 NAME TEXT NOT NULL
                 );''')

        con.execute('''CREATE TABLE IF NOT EXISTS History(
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    TOOL_ID INTEGER NOT NULL,
                    EMPLOYEE TEXT NOT NULL,
                    FROM_DATE DATETIME NOT NULL,
                    TO_DATE DATETIME NOT NULL,
                    FOREIGN KEY (TOOL_ID) REFERENCES Tools(ID) ON DELETE CASCADE
                );''')


if __name__ == '__main__':
    setup()
    app.run(debug=True, host='0.0.0.0', port=5000)
