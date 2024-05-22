const fs = require("fs");
const path = require("path");

let data = fs.readFile(
    path.join(__dirname, "example.txt"),
    "utf-8",
    (err, data) => {
        if (err) {
            console.error(err);
            return;
        }
        console.log(data);
    }
);
