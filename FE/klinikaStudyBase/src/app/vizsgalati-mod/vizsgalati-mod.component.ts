import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {VizsgalatiModDetailDto} from "../dto/vizsgalatiModDetailDto";
import {Router} from "@angular/router";
import {VizsgalatiModService} from "../service/data/vizsgalati-mod/vizsgalati-mod.service";

@Component({
  selector: 'app-vizsgalati-mod',
  templateUrl: './vizsgalati-mod.component.html',
  styleUrls: ['./vizsgalati-mod.component.css']
})
export class VizsgalatiModComponent implements OnInit {

  title = 'Vizsgálati módok'
  deleteSuccess = false;
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: VizsgalatiModDetailDto[] = [];
  dataSource = new MatTableDataSource<VizsgalatiModDetailDto>(this.ELEMENTDATA)
  displayedColumns: string[] = ['id', 'hivatkozasiNev', 'megnevezes', 'eur', 'huf', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: VizsgalatiModService,
    private route: Router,
  ) {
  }

  ngOnInit(): void {
    this.refreshAll()
  }

  refreshAll() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as VizsgalatiModDetailDto[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  navigateUjVm(id: number) {
    this.route.navigate(['vm/new', id]);
  }

}
