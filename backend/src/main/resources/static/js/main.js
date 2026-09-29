let activities = [];


const activitiesGrid = document.querySelector("#activities-grid");
const filterButtons = document.querySelectorAll(".filter-button");
const searchInput = document.querySelector("#activity-search-input");
const noActivitiesMessage = document.querySelector("#no-activities-message");
const activitiesStatus = document.querySelector("#activities-status");


let selectedCategory = "All";
let searchTerm = "";


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


function applyFilters() {
    const filteredActivities = activities.filter((activity) => {
        const matchesCategory =
            selectedCategory === "All" ||
            activity.category === selectedCategory;

        const matchesSearch =
            activity.title.toLowerCase().includes(searchTerm) ||
            activity.category.toLowerCase().includes(searchTerm);

        return matchesCategory && matchesSearch;
    });

    renderActivities(filteredActivities);
    noActivitiesMessage.hidden =
        activities.length === 0 || filteredActivities.length > 0;
}


filterButtons.forEach((button) => {
    button.addEventListener("click", () => {
        selectedCategory = button.dataset.category;

        filterButtons.forEach((filterButton) => {
            filterButton.classList.remove("active");
            filterButton.setAttribute("aria-pressed", "false");
        });

        button.classList.add("active");
        button.setAttribute("aria-pressed", "true");

        applyFilters();
    });
});


searchInput.addEventListener("input", () => {
    searchTerm = searchInput.value
        .trim()
        .toLowerCase();

    applyFilters();
});

function showStatus(message) {
    activitiesStatus.textContent = message;
    activitiesStatus.hidden = message === "";
}

async function loadActivities() {
    showStatus("Loading activities...");
    noActivitiesMessage.hidden = true;

    try {
        const response = await fetch("/activities");

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        activities = await response.json();

        if (activities.length === 0) {
            showStatus("No activities are available yet.");
        } else {
            showStatus("");
        }

        applyFilters();
    } catch (error) {
        console.error("Could not load activities:", error);
        showStatus("Could not load activities. Please try again later.");
        noActivitiesMessage.hidden = true;
        activitiesGrid.innerHTML = "";
    }
}

loadActivities();