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

// get all orders
app.get('/orders', (req, res) => {
    console.log('orders')
    pool.getConnection ((err, connection) => {
        if(err) throw err
        console.log(`connected as id ${connection.threadId}`)
        connection.query('SELECT * from orders', (err, rows) => {
            connection.release () // return the connection to pool
            if(!err) {
                res.send(rows)
            } else {
                console.log(err)
            }
        })
    })
})

// get all users
app.get('/users', (req, res) => {
    console.log('users')
    pool.getConnection ((err, connection) => {
        if(err) throw err
        console.log(`connected as id ${connection.threadId}`)
        connection.query('SELECT * from users', (err, rows) => {
            connection.release () // return the connection to pool
            if(!err) {
                res.send(rows)
            } else {
                console.log(err)
            }
        })
    })
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
        console.log(`connected as id ${connection. threadId}`)

        connection.query('DELETE from movies WHERE id = ?', [req.params.id], (err, rows) => {
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

        const { id, name, description, genre, img, imdb, year, country, director, actors, language, tickets, price } = req.body

        connection.query('UPDATE movies SET name = ?, description = ?, genre = ?, img = ?, imdb = ?, year = ?, country = ?, director = ?, actors = ?, language = ?, price = ?, tickets = ? WHERE id = ? ', [name, description,
        genre, img, imdb, year, country, director, actors, language, price, tickets, id,], (err, rows) => {
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