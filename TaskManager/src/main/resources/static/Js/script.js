const baseUrl = "http://localhost:8080";
console.log("JS loaded");
// 🔹 Load Users
function loadUsers() {
    fetch(baseUrl + "/users")
        .then(res => res.json())
        .then(data => {
            console.log("Users:", data);

            let html = "";
            data.forEach(u => {
                html += `
                <li>
                    ${u.name} (${u.email})
                    <br>
                    <button onclick="deleteUser(${u.id})">Delete</button>
                </li>`;
            });

            document.getElementById("users").innerHTML = html;
        })
        .catch(err => console.error("User Load Error:", err));
}

// 🔹 Add User
function addUser() {

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!name || !email || !password) {
        alert("Saare fields fill kar");
        return;
    }

    fetch("http://localhost:8080/users", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ name, email, password })
    })
    .then(async res => {
        const text = await res.text(); // 🔥 IMPORTANT

        if (!res.ok) {
            console.error("Backend Error:", text); // 👈 yahi sach batayega
            throw new Error("Server error: " + res.status);
        }

        return JSON.parse(text);
    })
    .then(data => {
        console.log("User Saved:", data);
        loadUsers();
    })
    .catch(err => console.error(err));
}
// 🔹 Delete User
function deleteUser(id) {
    fetch(baseUrl + "/users/" + id, {
        method: "DELETE"
    })
    .then(() => {
        console.log("User Deleted:", id);
        loadUsers();
    })
    .catch(err => console.error("Delete User Error:", err));
}

// 🔹 Load Tasks
function loadTasks() {
    fetch(baseUrl + "/tasks")
        .then(res => res.json())
        .then(data => {
            console.log("Tasks:", data);

            let html = "";
            data.forEach(t => {
                html += `
                <li>
                    <b>${t.title}</b> - ${t.status}
                    <br>
                    ${t.description}
                    <br>
                    <button onclick="deleteTask(${t.id})">Delete</button>
                </li>`;
            });

            document.getElementById("tasks").innerHTML = html;
        })
        .catch(err => console.error("Task Load Error:", err));
}

// 🔹 Add Task
function addTask() {
    console.log("Add Task clicked");

    fetch(baseUrl + "/tasks", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            title: document.getElementById("title").value,
            description: document.getElementById("desc").value,
            status: document.getElementById("status").value,
            userId: document.getElementById("userId").value
        })
    })
    .then(res => res.json())
    .then(data => {
        console.log("Task Saved:", data);
        loadTasks();
        clearTaskInputs();
    })
    .catch(err => console.error("Add Task Error:", err));
}

// 🔹 Delete Task
function deleteTask(id) {
    fetch(baseUrl + "/tasks/" + id, {
        method: "DELETE"
    })
    .then(() => {
        console.log("Task Deleted:", id);
        loadTasks();
    })
    .catch(err => console.error("Delete Task Error:", err));
}

// 🔹 Clear Inputs
function clearUserInputs() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}

function clearTaskInputs() {
    document.getElementById("title").value = "";
    document.getElementById("desc").value = "";
    document.getElementById("status").value = "";
    document.getElementById("userId").value = "";
}

// 🔹 Page load pe data load
window.onload = function () {
    loadUsers();
    loadTasks();
};