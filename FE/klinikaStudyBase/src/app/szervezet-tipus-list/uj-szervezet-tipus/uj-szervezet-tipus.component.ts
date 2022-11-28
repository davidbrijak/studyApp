import {Component, OnInit, ViewChild} from '@angular/core';
import {SzervezetTipus} from "../szervezet-tipus-list.component";
import {ActivatedRoute, Router} from "@angular/router";
import {SzervezetService} from "../../service/data/szervezet.service";
import {SzervezetTipusService} from "../../service/data/szervezet-tipus.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {Szervezet} from "../../szervezet-list/szervezet-list.component";

@Component({
  selector: 'app-uj-szervezet-tipus',
  templateUrl: './uj-szervezet-tipus.component.html',
  styleUrls: ['./uj-szervezet-tipus.component.css']
})
export class UjSzervezetTipusComponent implements OnInit {

  id: number;
  szervezetTipus: SzervezetTipus;
  message: string;
  isSuccesSave: boolean;
  title = 'Szervezet típus felvétele'
  isDisabled: boolean;

  deleteSuccess = false;
  ELEMENTDATA: Szervezet[] = [];
  dataSource = new MatTableDataSource<Szervezet>(this.ELEMENTDATA)
  displayedColumns: string[] = ['id', 'nev', 'adoszam', 'szervezetTipusNev', 'cim', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  error: string
  isSmthEmpty: boolean

  constructor(private service: SzervezetTipusService, private szervezetService: SzervezetService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.isDisabled = this.route.snapshot.params['isDisabled'];
    this.szervezetTipus = new SzervezetTipus(this.id, '', '')
    if (this.id != -1) {
      this.title = 'Szervezet típus szerkesztése'
      this.service.get(this.id).subscribe(
        data => this.szervezetTipus = data
      )
      this.refreshSzervezet()
    }
  }

  handleSave(szervezetTipus: SzervezetTipus) {
    if (!szervezetTipus.nev.length || !szervezetTipus.hivatkozasiNev.length) {
      console.log('asd')
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
      return
    }
    this.service.create(szervezetTipus).subscribe(
      response => {
        this.message = "A mentés sikeres volt."
        this.isSuccesSave = true;
        this.router.navigate(['szervezetTipus/list']);
      }
    )
  }

  refreshSzervezet() {
    this.szervezetService.getAllByTipus(this.id).subscribe(
      response => {
        this.dataSource.data = response as Szervezet[];
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
      }
    );
    this.refreshSzervezet();
  }

  deleteSzervezet(id: number) {
    this.szervezetService.delete(id).subscribe(
      () => {
        this.deleteSuccess = true
        this.message + ':' + {id}
        this.refreshSzervezet();
      }
    );
  }

  navigateUjSzervezet(id: number) {
    this.router.navigate(['szervezet/new', id]);
  }
}
