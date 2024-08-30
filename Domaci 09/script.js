document.addEventListener("DOMContentLoaded", function () {
  const homeworkContainer = document.getElementById("homeworkContainer");
  const checkConditionBtn = document.getElementById("checkCondition");
  const calculateGradeBtn = document.getElementById("calculateGrade");
  const resultDiv = document.getElementById("result");
  const gradeResultDiv = document.getElementById("gradeResult");

  const ime = document.getElementById("ime");
  const text = ime.textContent.split(" ");

  // Kreiranje 15 polja za unos domaćih zadataka
  for (let i = 1; i <= 15; i++) {
    const label = document.createElement("label");
    label.textContent = `Domaći zadatak ${i}:`;
    const input = document.createElement("input");
    input.type = "number";
    input.id = `homework${i}`;
    input.required = true;
    homeworkContainer.appendChild(label);
    homeworkContainer.appendChild(input);
    homeworkContainer.appendChild(document.createElement("br"));
  }

  checkConditionBtn.addEventListener("click", checkCondition);
  calculateGradeBtn.addEventListener("click", calculateGrade);

  function checkCondition() {
    let totalPoints = 0;
    for (let i = 1; i <= 15; i++) {
      totalPoints += Number(document.getElementById(`homework${i}`).value);
    }
    totalPoints += Number(document.getElementById("projectPoints").value);
    totalPoints -= Number(document.getElementById("negativePoints").value);

    const hasCondition = totalPoints >= 35;
    const resultText = `${text[0]} ${text[1]} – ${hasCondition ? "ima" : "nema"} uslov`;
    const conditionClass = hasCondition ? "has-condition" : "no-condition";

    resultDiv.innerHTML = `<span class="${conditionClass}">${resultText}</span> <span class="italic">za izlazak na ispit</span>`;
  }

  function calculateGrade() {
    let totalPoints = 0;
    for (let i = 1; i <= 15; i++) {
      totalPoints += Number(document.getElementById(`homework${i}`).value);
    }
    totalPoints += Number(document.getElementById("projectPoints").value);
    totalPoints -= Number(document.getElementById("negativePoints").value);
    totalPoints += Number(document.getElementById("examPoints").value);

    let grade;
    if (totalPoints <= 50) grade = 5;
    else if (totalPoints <= 60) grade = 6;
    else if (totalPoints <= 70) grade = 7;
    else if (totalPoints <= 80) grade = 8;
    else if (totalPoints <= 90) grade = 9;
    else grade = 10;

    gradeResultDiv.textContent = `Konačna ocena: ${grade}`;
  }
});
