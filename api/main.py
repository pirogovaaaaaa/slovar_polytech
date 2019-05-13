#!flask/bin/python
from flask import Flask, jsonify
import postgresql
from urllib.parse import unquote


app = Flask(__name__)


def db_conn():
    """connect to db"""
    return postgresql.open('pq://postgres:password@localhost:5432/date')


@app.route('/api/v1.0/terms/<string:naprav>', methods=['GET'])
def get_terms(naprav):
    """return term, opred_id and naprav from db"""
    with db_conn() as db:
        terms = db.query('SELECT term, opred_id, naprav FROM terms\n'
                         '        WHERE naprav = $1', naprav)
        return jsonify({'terms': terms}), 200


@app.route('/api/v1.0/opreds/<int:opred_id>', methods=['GET'])
def get_opreds(opred_id):
    """return opreds from db"""
    with db_conn() as db:
        opreds = db.query('SELECT opred_id, opred FROM opreds\n'
                          '        WHERE opred_id = $1', opred_id)
        return jsonify({'opreds': opreds}), 200


@app.route('/api/v1.0/napravs/', methods=['GET'])
def get_fktheplc():
    """return naprav from db"""
    with db_conn() as db:
        napravs = db.query('SELECT DISTINCT naprav FROM terms')
        return jsonify({'napravs': napravs}), 200


@app.route('/api/v1.0/term/<string:term>', methods=['GET'])
def get_acab(term):
    """return term, opred_id and naprav from db"""
    term = unquote(term)
    with db_conn() as db:
        term = db.query('SELECT term, opred_id, naprav FROM terms\n'
                         '        WHERE lower(term) = lower($1)', term)
        return jsonify({'term': term}), 200


if __name__ == '__main__':
    """disable debug mode and enable access from internet"""
    app.run(host='0.0.0.0', debug=False)
