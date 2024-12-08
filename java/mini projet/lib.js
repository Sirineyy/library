// Borrow Book Function
function borrowBook() {
    // Get values from the form
    var memberName = document.getElementById("borrow-member").value;
    var bookTitle = document.getElementById("borrow-title").value;

    // Send AJAX request to borrow the book
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "borrow", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Prepare the data to send
    var data = "borrow-member=" + memberName + "&borrow-title=" + bookTitle;

    // Handle the response
    xhr.onload = function() {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            alert(response.message);  // Show success or error message
        } else {
            alert("Something went wrong. Try again.");
        }
    };

    // Send the request
    xhr.send(data);
}

// Return Book Function
function returnBook() {
    // Get values from the form
    var memberName = document.getElementById("return-member").value;
    var bookTitle = document.getElementById("return-title").value;

    // Send AJAX request to return the book
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "return", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Prepare the data to send
    var data = "return-member=" + memberName + "&return-title=" + bookTitle;

    // Handle the response
    xhr.onload = function() {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            alert(response.message);  // Show success or error message
        } else {
            alert("Something went wrong. Try again.");
        }
    };

    // Send the request
    xhr.send(data);
}

// Add New Book Function
function addBook() {
    // Get values from the form
    var bookTitle = document.getElementById("newtitle").value;
    var authorName = document.getElementById("new-author").value;

    // Send AJAX request to add the new book
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "addBook", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Prepare the data to send
    var data = "newtitle=" + bookTitle + "&new-author=" + authorName;

    // Handle the response
    xhr.onload = function() {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            alert(response.message);  // Show success or error message
        } else {
            alert("Something went wrong. Try again.");
        }
    };

    // Send the request
    xhr.send(data);
}

// Search Book Function
function searchBook() {
    // Get search query
    var query = document.getElementById("search").value;

    // Send AJAX request to search for books
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "search?query=" + query, true);

    // Handle the response
    xhr.onload = function() {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            displaySearchResults(response.books);  // Display books in the search results
        } else {
            alert("Something went wrong. Try again.");
        }
    };

    // Send the request
    xhr.send();
}

// Display Search Results
function displaySearchResults(books) {
    var searchResults = document.getElementById("search-results");
    searchResults.innerHTML = "";  // Clear previous results

    // Loop through the books and display them
    books.forEach(function(book) {
        var listItem = document.createElement("li");
        listItem.classList.add("list-group-item");
        listItem.innerText = "Title: " + book.title + " | Author: " + book.author;
        searchResults.appendChild(listItem);
    });
}
