import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {FizetesiPeriodusService} from "../service/data/fizetesi-periodus.service";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-fizetesi-periodus-list',
  templateUrl: './fizetesi-periodus-list.component.html',
  styleUrls: ['./fizetesi-periodus-list.component.css']
})
export class FizetesiPeriodusListComponent implements OnInit {

  title = 'Fizetési periódusok'
  deleteSuccess = false;
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: FizetesiPeriodus[] = [];
  dataSource = new MatTableDataSource<FizetesiPeriodus>(this.ELEMENTDATA)
  displayedColumns: string[] = ['id', 'hivatkozási név', 'megn', 'napok', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    protected service: FizetesiPeriodusService,
    protected route: Router,
  ) {
  }

  ngOnInit(): void {
    this.refreshAll()
  }

  refreshAll() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as FizetesiPeriodus[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  navigateUjFizetesiPeriodus(id: number, isDisabled: boolean) {
    this.route.navigate(['fizetesiPeriodus/new', id, isDisabled]);
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      () => {
        this.deleteSuccess = true
        this.message + ':' + {id}
        this.refreshAll();
      }
    );
  }

}

export class FizetesiPeriodus {
  id: number;
  hivatkozasiNev: string;
  megnevezes: string;
  napok: number;

  constructor(id: number, hivatkozasiNev: string, megnevezes: string, napok: number) {
    this.id = id;
    this.hivatkozasiNev = hivatkozasiNev;
    this.megnevezes = megnevezes;
    this.napok = napok;
  }

}
