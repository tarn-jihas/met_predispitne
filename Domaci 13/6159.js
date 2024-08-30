console.log((6159 % 7) + 1);
const sektor = [
  [
    ["naziv", "IT Razvoj"],
    ["brojZaposlenih", 12],
    ["rukovodilac", "Jane Doe"],
    ["tip", "IT"],
  ],
  [
    ["naziv", "HR Regrutacija"],
    ["brojZaposlenih", 5],
    ["rukovodilac", "John Doe"],
    ["tip", "HR"],
  ],
  [
    ["naziv", "Web Tim"],
    ["brojZaposlenih", 18],
    ["rukovodilac", "John Doe"],
    ["tip", "IT"],
  ],
  [
    ["naziv", "Trening i Razvoj"],
    ["brojZaposlenih", 7],
    ["rukovodilac", "Jane Doe"],
    ["tip", "HR"],
  ],
  [
    ["naziv", "Mobile Aplikacije"],
    ["brojZaposlenih", 15],
    ["rukovodilac", "John Doe"],
    ["tip", "IT"],
  ],
  [
    ["naziv", "DevOps Tim"],
    ["brojZaposlenih", 9],
    ["rukovodilac", "Jane Doe"],
    ["tip", "IT"],
  ],
  [
    ["naziv", "Korporativna Kultura"],
    ["brojZaposlenih", 4],
    ["rukovodilac", "John Doe"],
    ["tip", "HR"],
  ],
  [
    ["naziv", "Data Science"],
    ["brojZaposlenih", 11],
    ["rukovodilac", "Jane Doe"],
    ["tip", "IT"],
  ],
  [
    ["naziv", "Kompenzacije i Beneficije"],
    ["brojZaposlenih", 6],
    ["rukovodilac", "John Doe"],
    ["tip", "HR"],
  ],
  [
    ["naziv", "Cyber Security"],
    ["brojZaposlenih", 8],
    ["rukovodilac", "Jane Doe"],
    ["tip", "IT"],
  ],
];

function kreirajObjekte(sektor) {
  const itSektor = [];
  const hrSektor = [];

  sektor.forEach((s) => {
    const obj = Object.fromEntries(s);
    if (obj.tip === "IT") {
      itSektor.push(obj);
    } else if (obj.tip === "HR") {
      hrSektor.push(obj);
    }
  });

  return { itSektor, hrSektor };
}

function dodajPlateZaposlenih(itSektor, hrSektor) {
  itSektor.forEach((s) => {
    s.plateZaposlenih = (1000000 / s.brojZaposlenih) * 0.8 + 5000;
  });

  hrSektor.forEach((s) => {
    s.plateZaposlenih = (1000000 / s.brojZaposlenih) * 0.8;
  });
}

function prebrojSektoreVecikhPlata(itSektor, hrSektor) {
  return itSektor.concat(hrSektor).filter((s) => s.plateZaposlenih > 10000)
    .length;
}

function toString(itSektor, hrSektor) {
  let output = "IT Sektori:\n";
  itSektor.forEach((s) => {
    output += `${s.naziv} - Zaposleni: ${s.brojZaposlenih}, Rukovodilac: ${
      s.rukovodilac
    }, Prosečna plata: ${s.plateZaposlenih.toFixed(2)}\n`;
  });

  output += "\nHR Sektori:\n";
  hrSektor.forEach((s) => {
    output += `${s.naziv} - Zaposleni: ${s.brojZaposlenih}, Rukovodilac: ${
      s.rukovodilac
    }, Prosečna plata: ${s.plateZaposlenih.toFixed(2)}\n`;
  });

  return output;
}

const { itSektor, hrSektor } = kreirajObjekte(sektor);
dodajPlateZaposlenih(itSektor, hrSektor);
const brojSektoraVelikihPlata = prebrojSektoreVecikhPlata(itSektor, hrSektor);

const outputElement = document.getElementById("output");
outputElement.innerHTML = `<pre>${toString(
  itSektor,
  hrSektor
)}\nBroj sektora sa prosečnom platom većom od 10000: ${brojSektoraVelikihPlata}</pre>`;
