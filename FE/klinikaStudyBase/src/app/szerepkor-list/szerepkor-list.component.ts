import {Component, OnInit, ViewChild} from '@angular/core';
import {SzerepkorServiceService} from "../service/data/szerepkor-service.service";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-szerepkor-list',
  templateUrl: './szerepkor-list.component.html',
  styleUrls: ['./szerepkor-list.component.css']
})
export class SzerepkorListComponent implements OnInit {

  title = 'Szerepkörök';
  deleteSuccess = false;
  message = 'A törlés sikeres volt.';
  ELEMENTDATA: Szerepkor[] = [];
  displayedColumns: string[] = ['id', 'hivatkozási név', 'megn', 'kif', 'actions'];
  dataSource = new MatTableDataSource<Szerepkor>(this.ELEMENTDATA);
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: SzerepkorServiceService,
     private route: Router,
  ) {
  }

  ngOnInit(): void {
    this.refreshAllUsers()
  }

  refreshAllUsers() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as Szerepkor[];
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
        this.refreshAllUsers()
      }
    );
  }

  navigateUj(id: number, isDisabled: boolean) {
    this.route.navigate(['szerepkor/new', id, isDisabled]);
  }
}

export class Szerepkor {
  id: number;
  hivatkozasiNev: string;
  megnevezes: string;
  kifizetesAranya: number;

  constructor(id: number,
              hivatkozasiNev: string,
              megnevezes: string,
              kifizetesAranya: number) {
    this.id = id;
    this.hivatkozasiNev = hivatkozasiNev;
    this.megnevezes = megnevezes;
    this.kifizetesAranya = kifizetesAranya
  }
}
