async function getAllCourses(){
    const tbody = document.getElementById("tbody");

    fetch('http://localhost:8080/api/courses')
        .then(response => response.json())
        .then(courses => courses.forEach(course => {

            let row = document.createElement("tr");
            let name = document.createElement("td");
            let category = document.createElement("td");
            let date = document.createElement("td");
            let author = document.createElement("td");
            let price = document.createElement("td");

            row.scope = "row";
            name.textContent = course.courseName;
            category.textContent = course.category;
            date.textContent = course.date;
            author.textContent = course.author;
            price.textContent = "$" + course.price ;

            row.appendChild(name);
            row.appendChild(category);
            row.appendChild(date);
            row.appendChild(author);
            row.appendChild(price);

            tbody.appendChild(row);
        }))
}