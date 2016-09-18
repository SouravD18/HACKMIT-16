var express = require('express');
var app = express();
var sqlite3 = require('sqlite3').verbose();
var db = new sqlite3.Database('test.db');
var bodyParser = require('body-parser');


app.use(bodyParser.json());
app.post('/delete', function (req,res) {
  const id = req.body.id;
  db.serialize(function() {
    var stmt = db.prepare("DELETE FROM toilet WHERE rowid=(?)");
    stmt.run(id)
    db.each("SELECT rowid AS id FROM toilet", function(err, row) {
        console.log(row.id);
    });
  });

  res.send('gucci');
})

app.post('/add', function(req,res) {
  body = req.body
  var stmt = db.prepare("INSERT INTO toilet VALUES (?, ?, ?, ?, ?)");
  stmt.run(body.latitude, body.longitude, body.building, body.floor, body.gender);
  db.each("SELECT rowid AS id FROM toilet", function(err, row) {
      console.log(row.id);
  });

  res.send('gucci');
})
app.get('/', function (req, res) {
  db.serialize(function() {
    // db.run("CREATE TABLE toilet (latitude Decimal(8,6), longitude Decimal(9,6), building INT, floor INT, gender INT)");
    // var stmt = db.prepare("INSERT INTO toilet VALUES (?, ?, ?, ?, ?)");
    // for (var i = 0; i < 10; i++) {
    //     stmt.run(i, i, i, i, i);
    // }
    db.each("SELECT rowid AS id FROM toilet", function(err, row) {
        console.log(row.id);
    });
  });

  res.send('Hello World!');
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
