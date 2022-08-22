import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {Router} from "@angular/router";
import {KifizetesekService} from "../service/data/kifizetesek.service";

@Component({
  selector: 'app-kifizetesek',
  templateUrl: './kifizetesek.component.html',
  styleUrls: ['./kifizetesek.component.css']
})
export class KifizetesekComponent implements OnInit {

  title = 'Kifizetések ellenőrzése személyre bontva'
  deleteSuccess = false;
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: FizetesCheckListDto[] = [];
  dataSource = new MatTableDataSource<FizetesCheckListDto>(this.ELEMENTDATA)
  displayedColumns: string[] = ['szemelyId', 'szemely', 'osszeg', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: KifizetesekService,
    private route: Router) {}

  ngOnInit(): void {
    this.refreshAll()
  }

  refreshAll() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as FizetesCheckListDto[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  navigateUj(id: number) {
    this.route.navigate(['kif/detail', id]);
  }
}

export class FizetesCheckListDto {

  id: number;
  szemelyId: number;
  szemely: string;
  szerepkor: string;
  osszeg: string;
  fizetveBySzervezet: boolean;
  fizetveToSzemely: boolean;
  meresFeltoltesSzam: string;
  meresId: number;

  constructor(id: number, szemelyId: number, szemely: string, szerepkor: string, osszeg: string, fizetveBySzervezet: boolean,
              fizetveToSzemely: boolean, meresFeltoltesSzam: string, meresId: number) {
    this.id = id;
    this.szemely = szemely;
    this.szerepkor = szerepkor;
    this.osszeg = osszeg;
    this.fizetveBySzervezet = fizetveBySzervezet;
    this.fizetveToSzemely = fizetveToSzemely;
    this.szemelyId = szemelyId;
    this.meresId = meresId;
    this.szerepkor = szerepkor;
    this.meresFeltoltesSzam = meresFeltoltesSzam;
  }
}
