import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {SzervezetService} from "../service/data/szervezet.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-szervezet-list',
  templateUrl: './szervezet-list.component.html',
  styleUrls: ['./szervezet-list.component.css']
})
export class SzervezetListComponent implements OnInit {

  title = 'Új mérés felvétele'
  deleteSuccess = false;
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: Szervezet[] = [];
  dataSource = new MatTableDataSource<Szervezet>(this.ELEMENTDATA)
  displayedColumns: string[] = ['id', 'nev', 'adoszam', 'szervezetTipusNev', 'cim', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: SzervezetService,
    private route: Router,
  ) {
  }

  ngOnInit(): void {
    this.refreshSzervezet()
  }

  refreshSzervezet() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as Szervezet[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      response => {
        this.deleteSuccess = true
        this.message + ':' + {id}
      }
    );
    this.refreshSzervezet();
  }

  navigateUjSzervezet(id: number) {
    this.route.navigate(['szervezet/new', id]);
  }

}

export class Szervezet {
  id: number;
  nev: string;
  adoszam: string;
  cim: string;
  szervezetTipusNev: string;

  constructor(id: number, nev: string, adoszam: string, cim: string, szervezetTipusNev: string) {
    this.id = id;
    this.nev = nev;
    this.adoszam = adoszam;
    this.cim = cim;
    this.szervezetTipusNev = szervezetTipusNev;
  }
}

export class NewSzervezetDto {
  id: number;
  nev: string;
  adoszam: string;
  cim: string;
  szervezetTipusAzonosito: number | undefined;

  constructor(id: number, nev: string, adoszam: string, cim: string, szervezetTipusAzonosito: number) {
    this.id = id;
    this.nev = nev;
    this.adoszam = adoszam;
    this.cim = cim;
    this.szervezetTipusAzonosito = szervezetTipusAzonosito;
  }
}

export class NewMeresDto {
  id: number;
  datum: Date;
  feltoltesSzam: string;
  fizetve: boolean;
  megjegyzes: string;
  studyAzonosito: number;
  szemelyAzonositok: number [];
  vizsgalatiAzonositok: number [];

  constructor(id: number, datum: Date, feltoltesSzam: string, fizetve: boolean, megjegyzes: string,
              studyAzonosito: number, szemelyAzonositok: number [], vizsgalatiAzonositok: number []) {
    this.id = id;
    this.datum = datum;
    this.feltoltesSzam = feltoltesSzam;
    this.fizetve = fizetve;
    this.megjegyzes = megjegyzes;
    this.studyAzonosito = studyAzonosito;
    this.szemelyAzonositok = szemelyAzonositok;
    this.vizsgalatiAzonositok = vizsgalatiAzonositok;
  }
}
