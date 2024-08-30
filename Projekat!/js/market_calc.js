document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("kalkulatorForma");
  const platformSelect = document.getElementById("platform");
  const trajanjeInput = document.getElementById("trajanje");
  const budzetInput = document.getElementById("budzet");
  const ciljnaGrupaSelect = document.getElementById("ciljnaGrupa");
  const tipKampanjeSelect = document.getElementById("tipKampanje");
  const rezultatDiv = document.getElementById("rezultat");
  const listaIstorije = document.getElementById("listaIstorije");
  const roiPrognozaDiv = document.getElementById("roiPrognoza");

  form.addEventListener("submit", function (e) {
    e.preventDefault();
    izracunajCenu();
  });

  function izracunajCenu() {
    const platform = platformSelect.value;
    const trajanje = parseInt(trajanjeInput.value);
    const budzet = parseFloat(budzetInput.value);
    const ciljnaGrupa = ciljnaGrupaSelect.value;
    const tipKampanje = tipKampanjeSelect.value;

    let baznaStopa = getPlatformRate(platform);
    let ciljnaGrupaMultiplikator = getTargetGroupMultiplier(ciljnaGrupa);
    let tipKampanjeMultiplikator = getCampaignTypeMultiplier(tipKampanje);

    const ukupnaCena =
      trajanje *
      budzet *
      baznaStopa *
      ciljnaGrupaMultiplikator *
      tipKampanjeMultiplikator;

    prikaziRezultat(
      ukupnaCena,
      platform,
      trajanje,
      budzet,
      ciljnaGrupa,
      tipKampanje
    );
    dodajUIstoriju(
      ukupnaCena,
      platform,
      trajanje,
      budzet,
      ciljnaGrupa,
      tipKampanje
    );
    prikaziROIPrognozu(ukupnaCena, tipKampanje);
  }

  function getPlatformRate(platform) {
    const rates = {
      facebook: 1.2,
      instagram: 1.5,
      twitter: 1.1,
      linkedin: 1.8,
      tiktok: 1.3,
    };
    return rates[platform] || 1;
  }

  function getTargetGroupMultiplier(group) {
    const multipliers = {
      opsta: 1,
      mladi: 1.2,
      poslovni: 1.5,
      seniori: 1.1,
    };
    return multipliers[group] || 1;
  }

  function getCampaignTypeMultiplier(type) {
    const multipliers = {
      brending: 1,
      prodaja: 1.3,
      dogadjaj: 1.2,
      edukacija: 1.1,
    };
    return multipliers[type] || 1;
  }

  function prikaziRezultat(
    cena,
    platform,
    trajanje,
    budzet,
    ciljnaGrupa,
    tipKampanje
  ) {
    rezultatDiv.innerHTML = `
            <h2>Rezultat kalkulacije</h2>
            <p>Platforma: ${platform}</p>
            <p>Trajanje: ${trajanje} dana</p>
            <p>Dnevni budžet: ${budzet}€</p>
            <p>Ciljna grupa: ${ciljnaGrupa}</p>
            <p>Tip kampanje: ${tipKampanje}</p>
            <h3>Ukupna cena kampanje: ${cena.toFixed(2)}€</h3>
        `;
  }

  function dodajUIstoriju(
    cena,
    platform,
    trajanje,
    budzet,
    ciljnaGrupa,
    tipKampanje
  ) {
    const li = document.createElement("li");
    li.innerHTML = `Platforma: ${platform}, Trajanje: ${trajanje} dana, Budžet: ${budzet}€, Ciljna grupa: ${ciljnaGrupa}, Tip: ${tipKampanje}, Cena: ${cena.toFixed(
      2
    )}€`;
    listaIstorije.prepend(li);

    if (listaIstorije.children.length > 5) {
      listaIstorije.removeChild(listaIstorije.lastChild);
    }
  }

  function prikaziROIPrognozu(ukupnaCena, tipKampanje) {
    let ocekivaniROI;
    switch (tipKampanje) {
      case "brending":
        ocekivaniROI = 1.5;
        break;
      case "prodaja":
        ocekivaniROI = 2.5;
        break;
      case "dogadjaj":
        ocekivaniROI = 2.0;
        break;
      case "edukacija":
        ocekivaniROI = 1.8;
        break;
      default:
        ocekivaniROI = 1.5;
    }

    const ocekivaniPrihod = ukupnaCena * ocekivaniROI;
    const ocekivaniProfit = ocekivaniPrihod - ukupnaCena;

    roiPrognozaDiv.innerHTML = `
            <h2>ROI Prognoza</h2>
            <p>Očekivani ROI: ${ocekivaniROI.toFixed(2)}</p>
            <p>Očekivani prihod: ${ocekivaniPrihod.toFixed(2)}€</p>
            <p>Očekivani profit: ${ocekivaniProfit.toFixed(2)}€</p>
        `;
  }
});
