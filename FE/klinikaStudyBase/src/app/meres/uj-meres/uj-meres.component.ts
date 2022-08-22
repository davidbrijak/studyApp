import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SzemelyService} from "../../service/data/szemely.service";
import {Szemely} from "../../study/uj-study/uj-study.component";
import {VizsgalatiModDetailDto} from "../../dto/vizsgalatiModDetailDto";
import {VizsgalatiModService} from "../../service/data/vizsgalati-mod/vizsgalati-mod.service";
import {MeresService} from "../../service/data/meres.service";

@Component({
  selector: 'app-uj-meres',
  templateUrl: './uj-meres.component.html',
  styleUrls: ['./uj-meres.component.css']
})
export class UjMeresComponent implements OnInit {

  id: number;
  meres: NewMeres;
  message: string;
  isSuccesSave: boolean;
  title = 'Mérés felvétele'
  selectedAlany: Szemely;
  selected_operator_1: Szemely;
  selected_operator_2: Szemely;
  selected_orvos: Szemely;
  datum: Date
  szemelyList: Szemely[] = [];
  vizsgalatiModList: VizsgalatiModDetailDto[] = [];
  selectedVizsgalatiMod: VizsgalatiModDetailDto;
  fizetve: boolean;
  studyAzonosito: number
  szerkText = 'Szerkesztés feloldása';
  isEditable = true
  error: string
  isSmthEmpty: boolean

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private szemelyService: SzemelyService,
    private vmService: VizsgalatiModService,
    private meresService: MeresService
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    if (this.id != -1) {
      this.isEditable = false
      this.title = 'Meres szerkesztése'
      this.meresService.get(this.id).subscribe(
        data => {
          this.meres = data
        }
      )    }
    this.refreshSzemelyek()
    this.refreshVms()
    this.meres = new NewMeres(-1, undefined, new Date(), '', false, '',
      undefined,undefined, 0, undefined, undefined)
  }

  refreshSzemelyek() {
    this.szemelyService.getAll().subscribe(
      response => {
        this.szemelyList = response as Szemely[];
      }
    );
  }

  refreshVms() {
    this.vmService.getAll().subscribe(
      response => {
        this.vizsgalatiModList = response as VizsgalatiModDetailDto[];
      }
    );
  }

  handleSave(meres: NewMeres) {
    meres.id = this.route.snapshot.params['id'];

    if (meres.id == -1 && (!meres.datum || !this.selectedAlany || !meres.megjegyzes || !this.selected_orvos ||
      !this.selectedVizsgalatiMod || !meres.feltoltesSzam)) {
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
      return
    }

    meres.alany = this.selectedAlany === undefined ? this.meres.alany : this.selectedAlany;
    meres.datum = this.datum === undefined ? this.meres.datum : this.datum;
    meres.operator_1 = this.selected_operator_1 === undefined ? this.meres.operator_1 : this.selected_operator_1;
    meres.operator_2 = this.selected_operator_2 === undefined ? this.meres.operator_2 : this.selected_operator_2;
    meres.fizetve = this.fizetve === undefined ? this.meres.fizetve : this.fizetve;
    meres.studyAzonosito = this.studyAzonosito === undefined ? this.meres.studyAzonosito : this.studyAzonosito;
    meres.vizsgalatiMod = this.selectedVizsgalatiMod === undefined ? this.meres.vizsgalatiMod : this.selectedVizsgalatiMod;
    meres.vizsgaloOrvos = this.selected_orvos === undefined ? this.meres.vizsgaloOrvos : this.selected_orvos;
    meres.studyAzonosito = this.route.snapshot.params['studyId'];

    this.meresService.create(meres).subscribe(
      () => {
        this.isSmthEmpty = true;
        this.message = "A mentés sikeres volt."
        this.error = "A mentés sikeres volt."
        this.isSuccesSave = true;
      }
    )
    //this.router.navigate(['study/new', this.studyAzonosito, false]);
    this.ngOnInit()
  }

  makeEditable() {
    this.isEditable = !this.isEditable
    this.szerkText = this.isEditable ? 'Szerkesztés zárolása' : 'Szerkesztés feloldása';
  }
}

  export class NewMeres {
  id: number;
  alany: Szemely | undefined;
  datum: Date;
  feltoltesSzam: string;
  fizetve: boolean;
  megjegyzes: string;
  operator_1: Szemely | undefined | null;
  operator_2: Szemely | undefined | null;
  studyAzonosito: number;
  vizsgalatiMod: VizsgalatiModDetailDto | undefined;
  vizsgaloOrvos: Szemely | undefined;

  constructor(id: number, alany: Szemely | undefined, datum: Date, feltoltesSzam: string, fizetve: boolean, megjegyzes: string,
              operator_1: Szemely | undefined, operator_2: Szemely | undefined, studyAzonosito: number, vizsgalatiMod: VizsgalatiModDetailDto | undefined,
              vizsgaloOrvos: Szemely | undefined) {
    this.id = id;
    this.alany = alany;
    this.datum = datum;
    this.feltoltesSzam = feltoltesSzam;
    this.fizetve = fizetve;
    this.megjegyzes = megjegyzes;
    this.operator_1 = operator_1;
    this.operator_2 = operator_2;
    this.studyAzonosito = studyAzonosito;
    this.vizsgalatiMod = vizsgalatiMod;
    this.vizsgaloOrvos = vizsgaloOrvos;
  }
}
