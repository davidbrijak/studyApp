import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {KifizetesekService} from "../../service/data/kifizetesek.service";
import {FizetesCheckListDto} from "../kifizetesek.component";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-kifizetes-detail',
  templateUrl: './kifizetes-detail.component.html',
  styleUrls: ['./kifizetes-detail.component.css']
})
export class KifizetesDetailComponent implements OnInit {

  title = 'Kifizetések ellenőrzése személyre bontva'
  id:number;
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: FizetesCheckListDto[] = [];
  dataSource = new MatTableDataSource<FizetesCheckListDto>(this.ELEMENTDATA)
  displayedColumns: string[] = ['szemely', 'szerepkor', 'osszeg', 'fizetveBySzervezet', 'fizetveToSzemely',
    'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: KifizetesekService,
    protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.refreshAll()
  }

  refreshAll() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.service.getById(this.id).subscribe(
      response => {
        this.dataSource.data = response as FizetesCheckListDto[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  handleSave(fizetesCheckListDtos: FizetesCheckListDto[]) {
    this.service.change(fizetesCheckListDtos).subscribe(
      () => {
        this.refreshAll()
      }
    )
  }
}


export class KifizetesChanged {
  meresId: number;
  fizetveBySzervezet:boolean;
  fizetveToSzemely: boolean;
  szerepkorHivatkozasiNev: String;


  constructor(meresId: number, fizetveBySzervezet: boolean, fizetveToSzemely: boolean, szerepkorHivatkozasiNev: String) {
    this.meresId = meresId;
    this.fizetveBySzervezet = fizetveBySzervezet;
    this.fizetveToSzemely = fizetveToSzemely;
    this.szerepkorHivatkozasiNev = szerepkorHivatkozasiNev;
  }
}
