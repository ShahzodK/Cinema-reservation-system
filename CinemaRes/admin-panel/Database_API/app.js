const express = require('express')
const bodyParser = require('body-parser')
const mysql = require('mysql')
const cors = require("cors");
const app = express()
const port = process.env.PORT || 5000
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

// CORS
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
    console.log('getted')
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
    
    // Delete a records movie
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
            } else {
                console.log(err)
            }
        })

        console.log(req.body)
    })
})

// Update a record / movies
app.put('/:id', (req, res) => {
    pool.getConnection ((err, connection) => {
        if(err) throw err
        console.log(`connected as id ${connection.threadId}`)

        const { id, name, description, genre, img, price } = req.body

        connection.query('UPDATE movies SET name = ?, description = ?, genre = ?, img = ?, price = ? WHERE id = ? ', [name, description,
        genre, img, price, id], (err, rows) => {
            connection.release() // return the connection to pool


                if(!err) {
                    connection.query('SELECT * from movies', (err, rows) => {
                        if(!err) {
                            res.send(rows)
                        } else {
                            console.log(err)
                        }
                    })
                    } else {
                    console.log(err)
                }

            })

            console.log(req.body)
    })
})

// Listen on enviroment port or 5000
app.listen (port, () => console.log(`Listen on port ${port}`))