import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {SzervezetTipusService} from "../service/data/szervezet-tipus.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-szervezet-tipus-list',
  templateUrl: './szervezet-tipus-list.component.html',
  styleUrls: ['./szervezet-tipus-list.component.css']
})
export class SzervezetTipusListComponent implements OnInit {

  title = 'Szervezet típusok'
  isDisabled = false;
  deleteSuccess = false;
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: SzervezetTipus[] = [];
  dataSource = new MatTableDataSource<SzervezetTipus>(this.ELEMENTDATA)
  displayedColumns: string[] = ['id', 'hivatkozasiNev', 'nev', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: SzervezetTipusService,
    private route: Router,
  ) {
  }

  ngOnInit(): void {
    this.refreshSzervezetTipus()
  }

  refreshSzervezetTipus() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as SzervezetTipus[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      () => {
        this.deleteSuccess = true
        this.message + ':' + {id}
        this.refreshSzervezetTipus();
      }
    );
  }

  navigateUjSzervezetTipus(id: number, isDisabled: boolean) {
    this.route.navigate(['szervezetTipus/new', id, isDisabled]);
  }
}

export class SzervezetTipus {
  id: number;
  hivatkozasiNev: string;
  nev: string;

  constructor(id: number, hivatkozasiNev: string, nev: string) {
    this.id = id;
    this.hivatkozasiNev = hivatkozasiNev;
    this.nev = nev;
  }
}
