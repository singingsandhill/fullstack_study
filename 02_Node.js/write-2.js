const path = require("path");
const fs = require("fs");

const text = fs.readFileSync(path.join(__dirname, "example.txt"), "utf-8");

if (fs.existsSync(path.join(__dirname, "text-1.txt"))) {
    console.log("text-1.txt already exists");
} else {
    fs.writeFile(path.join(__dirname, "text-1.txt"), text, (err) => {
        if (err) {
            console.log(err);
        } else {
            console.log("text-1.txt is saved");
        }
    });
}
// console.log(text);
// console.log(__filename);
