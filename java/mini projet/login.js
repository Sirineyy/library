document.getElementById("formlogin").addEventListener("submit",(event)=>{
    event.preventDefault();
    const username = document.getElementById("loginUsername").value;
    const cin = document.getElementById("logincin").value;
    const users = JSON.parse(localStorage.getItem("libraryusers")) || [];
    const user=users.find(user=>user.username===username &&user.cin===cin);
    if (user) {
        alert("Login successful!");
        window.location.href = "library.html"; 
    } else {
        document.getElementById("login-message").innerText = 'Invalid username or CIN!';
    }


   
});