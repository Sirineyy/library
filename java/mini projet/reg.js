
const commonpasscode = "fsm2024-2025";

document.getElementById("formreg").addEventListener("submit", (event) => {
    event.preventDefault();

    const username = document.getElementById("regUsername").value;
    const cin = document.getElementById("regcin").value;
    const librarypass = document.getElementById("librarypasscode").value;

    console.log("Username:", username);
    console.log("CIN:", cin);
    console.log("Library Pass:", librarypass);

    
    const users = JSON.parse(localStorage.getItem("libraryusers")) || [];
    const userExists = users.some(user => user.cin === cin || user.username === username);

    if (userExists) {
        document.getElementById("registration-message").innerText = "This user already exists!";
        setTimeout(() => {
            window.location.href = "index2.html"; 
        }, 1000); 
    } else if (librarypass === commonpasscode) {
        
        const user = { username, cin };
        users.push(user);
        localStorage.setItem("libraryusers", JSON.stringify(users));
        document.getElementById("registration-message").innerText = "Registration successfully! you may login now.";
        
        

        
        setTimeout(() => {
            window.location.href = "index2.html"; 
        }, 1000); 
    } else {
        // If the passcode is incorrect
        document.getElementById("registration-message").innerText = 'Incorrect library passcode!';
    }
});


































