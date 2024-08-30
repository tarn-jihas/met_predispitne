document.addEventListener("DOMContentLoaded", () => {
  const cards = document.querySelectorAll(".person-card");
  const grid = document.querySelector(".clanovi-grid");

  if (cards.length === 0) {
    console.error("No cards found");
    return;
  }

  if (!grid) {
    console.error("Grid container not found");
    return;
  }

  cards.forEach((card) => {
    card.addEventListener("click", () => {
      console.log("Card clicked");

      // Convert NodeList to an array
      const cardsArray = Array.from(cards);
      console.log("Original order:", cardsArray);

      // Shuffle Fisher-Yates algorithm
      for (let i = cardsArray.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [cardsArray[i], cardsArray[j]] = [cardsArray[j], cardsArray[i]];
      }
      console.log("Shuffled order:", cardsArray);

      // Clear the grid and append the shuffled cards with animation
      grid.innerHTML = "";
      cardsArray.forEach((shuffledCard) => {
        shuffledCard.classList.add("shuffle");
        grid.appendChild(shuffledCard);
      });

      // Remove the animation class after the animation ends
      setTimeout(() => {
        cardsArray.forEach((shuffledCard) => {
          shuffledCard.classList.remove("shuffle");
        });
      }, 500); // Match the duration of the animation

      console.log("Cards shuffled and appended");
    });
  });

  console.log("Event listeners attached");
});
