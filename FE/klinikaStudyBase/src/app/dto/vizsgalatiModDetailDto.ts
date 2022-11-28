export class VizsgalatiModDetailDto {

  id: number;
  hivatkozasiNev: string;
  megnevezes: string;
  eur: string;
  huf: string;

  constructor(id: number, hivatkozasiNev: string, megnevezes: string, eur: string, huf: string) {
    this.id = id;
    this.hivatkozasiNev = hivatkozasiNev;
    this.megnevezes = megnevezes;
    this.eur = eur;
    this.huf = huf;
  }

}
