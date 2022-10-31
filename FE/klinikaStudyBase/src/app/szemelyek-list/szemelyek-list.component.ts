import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {Szemely} from "../szemely/szemely.component";
import {SzemelyService} from "../service/data/szemely.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {Szerepkor} from "../szerepkor-list/szerepkor-list.component";
import {SzerepkorServiceService} from "../service/data/szerepkor-service.service";

@Component({
  selector: 'app-szemelyek-list',
  templateUrl: './szemelyek-list.component.html',
  styleUrls: ['./szemelyek-list.component.css']
})
export class SzemelyekListComponent implements OnInit {

  ELEMENTDATA: Szemely[] = [];
  displayedColumns: string[] = ['nev', 'hozzatartozoSzerepkorok'];
  dataSource = new MatTableDataSource<Szemely>(this.ELEMENTDATA);
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  nevSearch:string | null;
  szerepkorokSearch:string | null;
  szerepkorList: Szerepkor[] = [];
  selectedJogosultsag: Szerepkor[] = [];

  constructor(
    private service: SzemelyService,
    private route: Router,
    private szerepkorService: SzerepkorServiceService,
    private szemelyService: SzemelyService
  ) { }

  ngOnInit(): void {
    this.refreshAllSzemely();
    this.refreshSzerepkorok()
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

  refreshSzerepkorok() {
    this.szerepkorService.getAll().subscribe(
      response => {
        this.szerepkorList = response as Szerepkor[];
      }
    );
  }

  search(nevSearch: string | null, selectedJogosultsag: Szerepkor[]) {
    const filter = selectedJogosultsag.length ? new SzemelyFilter(nevSearch, selectedJogosultsag.map(jog => jog.id))
      : new SzemelyFilter(nevSearch, this.szerepkorList.map(jog => jog.id))
    this.szemelyService.search(filter).subscribe(
          response => {
            this.dataSource.data = response as Szemely[];
            this.dataSource.sort = this.sort
            this.dataSource.paginator = this.paginator
            for(let szerepkor of this.dataSource.data) {
              szerepkor.hozzatartozoSzerepkorok = szerepkor.szerepkorok?.map(szerepkor => szerepkor.megnevezes).join()
            }
        }
    )
  }

  clear() {
    this.nevSearch = null;
    this.szerepkorokSearch = null;
    this.ngOnInit()
  }
}

export class SzemelyFilter {
  nev: string | null;
  szerepkorAzonositok: number [];

  constructor(nev: string | null, szerepkorAzonositok: number []) {
    this.nev = nev;
    this.szerepkorAzonositok = szerepkorAzonositok
  }
}
