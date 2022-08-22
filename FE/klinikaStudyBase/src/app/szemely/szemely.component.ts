import { Component, OnInit } from '@angular/core';
import {Szerepkor} from "../szerepkor-list/szerepkor-list.component";

@Component({
  selector: 'app-szemely',
  templateUrl: './szemely.component.html',
  styleUrls: ['./szemely.component.css']
})
export class SzemelyComponent {
  constructor() { }
}

export class Szemely {
  id: number;
  email: string;
  nev: string;
  postaCim: string;
  tajszam: string;
  telefonSzam: string;
  szerepkorok: Szerepkor[] | undefined;
  szerepkorAzonositok: number[];
  hozzatartozoSzerepkorok: string | undefined;

  constructor(id: number, email: string, nev: string, postaCim: string, tajszam: string, telefonSzam: string,
              szerepkorAzonositok: number[], szerepkorok?: Szerepkor[] | undefined, hozzatartozoSzerepkorok?: string | undefined) {
    this.id = id;
    this.email = email;
    this.nev = nev;
    this.postaCim = postaCim;
    this.tajszam = tajszam;
    this.telefonSzam = telefonSzam;
    this.szerepkorAzonositok = szerepkorAzonositok;
    this.szerepkorok = szerepkorok
    this.hozzatartozoSzerepkorok = hozzatartozoSzerepkorok
  }
}
