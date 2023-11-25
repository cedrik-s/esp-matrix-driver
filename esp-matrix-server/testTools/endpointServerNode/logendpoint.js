const express = require('express')
const app = express()
const port = 3500
app.use(express.json())
app.post('/test', (req, res) => {
    console.log(JSON.stringify(req.body))
    res.send('Hello World!')
})
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
})