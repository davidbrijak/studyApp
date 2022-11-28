import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {Router} from "@angular/router";
import {JogosultsagService} from "../service/data/jogosultsag.service";

@Component({
  selector: 'app-jogosultsag',
  templateUrl: './jogosultsag-list.component.html',
  styleUrls: ['./jogosultsag-list.component.css']
})
export class JogosultsagListComponent implements OnInit {

  title = 'Jogosultságok'
  deleteSuccess = false;
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: JogosutsagDetailDto[] = [];
  dataSource = new MatTableDataSource<JogosutsagDetailDto>(this.ELEMENTDATA)
  displayedColumns: string[] = ['id', 'jogosultsagNev', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: JogosultsagService,
    private route: Router,
  ) {
  }

  ngOnInit(): void {
    this.refreshAll()
  }

  refreshAll() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as JogosutsagDetailDto[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  navigateUj(id: number) {
    this.route.navigate(['jogosultsag/new', id]);
  }
}

export class JogosutsagDetailDto {
  id: number;
  jogosultsagNev: string;

  constructor(id: number, jogosultsagNev: string) {
    this.id = id;
    this.jogosultsagNev = jogosultsagNev;
  }
}
