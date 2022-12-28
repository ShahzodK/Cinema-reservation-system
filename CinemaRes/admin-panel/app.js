const express = require('express')
const bodyParser = require('body-parser')
const mysql = require('mysql')
const app = express()
const port = process.env.PORT || 5000
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

// CORS
const cors = require("cors");
const corsOptions = {
   origin:'*', 
}

app.use(cors(corsOptions)) // Use this after the variable declaration

// MySQL

const pool = mysql.createPool({
    connectionLimit: 10,
    host : 'localhost',
    user : 'root',
    password : '',
    database : 'movies'
})

    // get all movies
app.get('', (req, res) => {
    pool.getConnection ((err, connection) => {
        if(err) throw err
        console.log(`connected as id ${connection.threadId}`)
        connection.query('SELECT * from movies', (err, rows) => {
            connection.release () // return the connection to pool
            if(!err) {
                res.send(rows)
            } else {
                console.log(err)
            }
        })
    })
})

    // get a movie by ID
app.get('/:id', (req, res) => {
    
    pool.getConnection ((err, connection) => {
        if(err) throw err
            console.log(`connected as id ${connection.threadId}`)

            connection.query('SELECT * from movies WHERE id = ?', [req.params.id], (err, rows) => {
                connection.release() // return the connection to pool

                    if(!err) {
                        res.send(rows)
                    } else {
                        console.log(err)
                    }
                })
            })
        })
    
    // Delete a records beer
app.delete('/:id', (req, res) => {
    pool.getConnection ((err, connection) => {
        if(err) throw err
        console.log('connected as id ${connection. threadId}')

        connection.query('DELETE from movies WHERE id = ?', [req.params.id], (err, rows) => {
            connection.release() // return the connection to pool
                if(!err) {
                    res.send(`Movie with the Record ID: ${[req.params.id]} has been removed.`)
                } else {
                    console.log(err)
                }
            })
        })
    })

// Add a record / movie
app.post('', (req, res) => {
    pool.getConnection ((err, connection) => {
        if(err) throw err
        console.log(`connected as id ${connection.threadId}`)
        
        const params = req.body
        connection.query('INSERT INTO movies SET ?', params, (err, rows) => {
            connection.release() // return the connection to pool
            if(!err) {
                res.send(`Movie with the name: ${params.name} has been added.`)
            } else {
                console.log(err)
            }
        })

        console.log(req.body)
    })
})

// Update a record / movies
app.put('', (req, res) => {
    pool.getConnection ((err, connection) => {

        if(err) throw err
        console.log('connected as id ${connection.threadId}')

        const { id, name, description, genre, price } = req.body

        connection.query('UPDATE beers SET name = ?, description = ?, genre = ?, price WHERE id = ?', [name, description,
        genre, price, id], (err, rows) => {
            connection.release() // return the connection to pool

                if(!err) {
                    res.send(`Beer with the name: ${name} has been added.`)
                } else {
                    console.log(err)
                }

            })

            console.log(req.body)
    })
})

// Listen on enviroment port or 5000
app.listen (port, () => console.log(`Listen on port ${port}`))