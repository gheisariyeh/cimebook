const activities = [
    {
        category: "Hiking",
        title: "Hiking at Semnoz",
        description:
            "A beginner-friendly hike with panoramic views over Lake Annecy and the surrounding mountains.",
        difficulty: "Beginner",
        duration: "4h",
        price: 45,
        image: "images/activities/hiking-semnoz.png"
    },
    {
        category: "Climbing",
        title: "Climbing in Talloires",
        description:
            "Discover climbing routes near Lake Annecy with a certified local guide.",
        difficulty: "Intermediate",
        duration: "3h",
        price: 60,
        image: "images/activities/climbing-talloires.png"
    },
    {
        category: "Canyoning",
        title: "Canyoning near Angon",
        description:
            "A refreshing canyoning experience for people looking for a more dynamic outdoor adventure.",
        difficulty: "Intermediate",
        duration: "Half-day",
        price: 75,
        image: "images/activities/canyoning-angon.png"
    }
];


const activitiesGrid = document.querySelector("#activities-grid");
const filterButtons = document.querySelectorAll(".filter-button");


function renderActivities(activityList) {
    activitiesGrid.innerHTML = "";
    activitiesGrid.classList.toggle(
        "single-result",
        activityList.length === 1
    );

    activityList.forEach((activity) => {
        const activityCard = document.createElement("article");

        activityCard.classList.add("activity-card");

        activityCard.innerHTML = `
            <div
                class="activity-image"
                style="background-image: url('${activity.image}')">
            </div>

            <div class="activity-content">

                <p class="activity-category">
                    ${activity.category}
                </p>

                <h3>
                    ${activity.title}
                </h3>

                <p>
                    ${activity.description}
                </p>

                <div class="activity-meta">
                    <span>${activity.difficulty}</span>
                    <span>${activity.duration}</span>
                    <span>From ${activity.price}€</span>
                </div>

            </div>
        `;

        activitiesGrid.appendChild(activityCard);
    });
}


filterButtons.forEach((button) => {
    button.addEventListener("click", () => {
        const selectedCategory = button.dataset.category;

        filterButtons.forEach((filterButton) => {
            filterButton.classList.remove("active");
            filterButton.setAttribute("aria-pressed", "false");
        });

        button.classList.add("active");
        button.setAttribute("aria-pressed", "true");

        if (selectedCategory === "All") {
            renderActivities(activities);
            return;
        }

        const filteredActivities = activities.filter(
            (activity) => activity.category === selectedCategory
        );

        renderActivities(filteredActivities);
    });
});


renderActivities(activities);