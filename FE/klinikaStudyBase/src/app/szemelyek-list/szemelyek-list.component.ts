import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {Szemely} from "../szemely/szemely.component";
import {SzemelyService} from "../service/data/szemely.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-szemelyek-list',
  templateUrl: './szemelyek-list.component.html',
  styleUrls: ['./szemelyek-list.component.css']
})
export class SzemelyekListComponent implements OnInit {

  ELEMENTDATA: Szemely[] = [];
  displayedColumns: string[] = ['id', 'nev', 'hozzatartozoSzerepkorok'];
  dataSource = new MatTableDataSource<Szemely>(this.ELEMENTDATA);
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  nevSearch:string | null;
  azonositoSearch:string | null;
  szerepkorokSearch:string | null;

  constructor(
    private service: SzemelyService,
    private route: Router,
  ) { }

  ngOnInit(): void {
    this.refreshAllSzemely()
  }

  refreshAllSzemely() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as Szemely[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
        for(let szerepkor of this.dataSource.data) {
          szerepkor.hozzatartozoSzerepkorok = szerepkor.szerepkorok?.map(szerepkor => szerepkor.megnevezes).join()
        }
      }
    );
  }

  search(azonositoSearch: string | null, nevSearch: string | null, szerepkorokSearch: string | null) {
    console.log(this.azonositoSearch, this.szerepkorokSearch, this.nevSearch)
  }

  clear(azonositoSearch: string | null, nevSearch: string | null, szerepkorokSearch: string | null) {
    this.azonositoSearch = null;
    this.nevSearch = null;
    this.szerepkorokSearch = null;
    this.ngOnInit()
  }
}

export class SzemelySearchRequest {
  azonositoSearch: string;
  nevSearch: string;
  szerepekor: number;


  constructor(azonositoSearch: string, nevSearch: string, szerepekor: number) {
    this.azonositoSearch = azonositoSearch;
    this.nevSearch = nevSearch;
    this.szerepekor = szerepekor;
  }
}
