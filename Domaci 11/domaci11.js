class Sektor {
  #naziv;
  #brZaposlenih;
  #plataZaposlenih;

  constructor(naziv, brZaposlenih) {
    this.#naziv = naziv;
    this.#brZaposlenih = brZaposlenih;
    this.#plataZaposlenih = new Map();
  }

  get naziv() {
    return this.#naziv;
  }
  set naziv(value) {
    this.#naziv = value;
  }

  get brZaposlenih() {
    return this.#brZaposlenih;
  }
  set brZaposlenih(value) {
    this.#brZaposlenih = value;
  }

  get plataZaposlenih() {
    return this.#plataZaposlenih;
  }

  racunajPlatu() {
    throw new Error("Apstraktna metoda mora biti implementirana u podklasi");
  }
}

class ITsektor extends Sektor {
  #email;

  constructor(naziv, brZaposlenih, email) {
    super(naziv, brZaposlenih);
    this.#email = email;
  }

  get email() {
    return this.#email;
  }
  set email(value) {
    this.#email = value;
  }

  racunajPlatu() {
    const plata = (1000000 / this.brZaposlenih) * 0.8 + 5000;
    for (let i = 1; i <= this.brZaposlenih; i++) {
      this.plataZaposlenih.set(`Zaposleni ${i}`, plata);
    }
  }
}

class HRsektor extends Sektor {
  #brojTelefona;

  constructor(naziv, brZaposlenih, brojTelefona) {
    super(naziv, brZaposlenih);
    this.#brojTelefona = brojTelefona;
  }

  get brojTelefona() {
    return this.#brojTelefona;
  }
  set brojTelefona(value) {
    this.#brojTelefona = value;
  }

  racunajPlatu() {
    const plata = (1000000 / this.brZaposlenih) * 0.8;
    for (let i = 1; i <= this.brZaposlenih; i++) {
      this.plataZaposlenih.set(`Zaposleni ${i}`, plata);
    }
  }
}

// Test
const itSektor = new ITsektor("IT Sektor", 10, "it@company.com");
const hrSektor = new HRsektor("HR Sektor", 5, "011-123-456");
const marketingSektor = new HRsektor("Marketing Sektor", 8, "011-789-012");

itSektor.racunajPlatu();
hrSektor.racunajPlatu();
marketingSektor.racunajPlatu();

console.log("IT Sektor plate:");
itSektor.plataZaposlenih.forEach((plata, zaposleni) => {
  console.log(`${zaposleni}: ${plata.toFixed(2)}`);
});

console.log("\nHR Sektor plate:");
hrSektor.plataZaposlenih.forEach((plata, zaposleni) => {
  console.log(`${zaposleni}: ${plata.toFixed(2)}`);
});

console.log("\nMarketing Sektor plate:");
marketingSektor.plataZaposlenih.forEach((plata, zaposleni) => {
  console.log(`${zaposleni}: ${plata.toFixed(2)}`);
});
