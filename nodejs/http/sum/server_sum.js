const express = require('express')
const app = express()
const port = 3000

app.get('/sum', (request, response) => {

  let a = parseInt(request.query.a, 10)
  let b = parseInt(request.query.b, 10)
  let sum = a + b
  response.end(`sum(${a}, ${b}) = ${sum}`)
})

app.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }
  console.log(`server is listening on ${port}`)
})
