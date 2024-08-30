document.getElementById("contactForm").addEventListener("submit", function (e) {
  e.preventDefault();
  if (validateForm()) {
    appendToList();
  }
});

function validateForm() {
  let isValid = true;
  const imePrezime = document.getElementById("imePrezime").value.trim();
  const naslov = document.getElementById("naslov").value.trim();
  const poruka = document.getElementById("poruka").value.trim();
  const telefon = document.getElementById("telefon").value.trim();
  const tip = document.getElementById("tip").value;

  // Validacija imena i prezimena
  if (!/^[a-zA-Z]+$/.test(imePrezime)) {
    document.getElementById("imePrezimeError").textContent =
      "Ime i prezime mora biti jedna reč bez brojeva.";
    isValid = false;
  } else {
    document.getElementById("imePrezimeError").textContent = "";
  }

  // Validacija naslova
  if (naslov === "") {
    document.getElementById("naslovError").textContent = "Naslov je obavezan.";
    isValid = false;
  } else {
    document.getElementById("naslovError").textContent = "";
  }

  // Validacija poruke
  if (poruka.length < 10 || poruka.length > 500) {
    document.getElementById("porukaError").textContent =
      "Poruka mora imati između 10 i 500 karaktera.";
    isValid = false;
  } else {
    document.getElementById("porukaError").textContent = "";
  }

  // Validacija telefona
  if (!/^\+?\d+$/.test(telefon)) {
    document.getElementById("telefonError").textContent =
      "Neispravan format telefona.";
    isValid = false;
  } else {
    document.getElementById("telefonError").textContent = "";
  }

  // Validacija tipa poruke
  if (tip === "") {
    document.getElementById("tipError").textContent = "Izaberite tip poruke.";
    isValid = false;
  } else {
    document.getElementById("tipError").textContent = "";
  }

  return isValid;
}

function appendToList() {
  const imePrezime = document.getElementById("imePrezime").value.trim();
  const naslov = document.getElementById("naslov").value.trim();
  const poruka = document.getElementById("poruka").value.trim();
  const telefon = document.getElementById("telefon").value.trim();
  const tip = document.getElementById("tip").value;

  const listItem = document.createElement("li");
  listItem.innerHTML = `<strong>Ime i prezime:</strong><br> ${imePrezime}<br>
                                  <strong>Naslov:</strong><br> ${naslov}<br>
                                  <strong>Poruka:</strong><br> ${poruka}<br>
                                  <strong>Telefon:</strong><br> ${telefon}<br>
                                  <strong>Tip:</strong><br> ${tip}`;

  document.getElementById("poslatePortuke").appendChild(listItem);

  // Resetovanje forme
  document.getElementById("contactForm").reset();
}
