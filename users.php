<?php
error_reporting(E_ALL);
ini_set('display errors',1);
$servername = "localhost";
$username = "phpuser";
$password = "phpuserpassword";
$dbname = "mydatabase";

$conn = new mysqli($servername, $username, $password, $dbname);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}



$sql = "SELECT * FROM USERS";

$result = $conn->query($sql);
echo "USERS TABLE";
if ($result->num_rows > 0) {
    echo "<table border='1'>";
    echo "<tr><th>ID</th><th>username</th><th>email</th></tr>";
    while($row = $result->fetch_assoc()) {
       echo " <tr>";
        echo "<td>" . $row["id"]  ."</td>";
        echo "<td>" . $row["username"] . "</td>";
        echo "<td>" . $row["email"] . "</td>";
        echo "</tr>";
    }
    echo "</table>";
} else {
    echo "0 results";
}


$conn->close();
?>
