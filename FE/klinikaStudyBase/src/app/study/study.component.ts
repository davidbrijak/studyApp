import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {StudyService} from "../service/data/study.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.css']
})
export class StudyComponent implements OnInit {

 title = 'Studyies'
  message = 'A törlés sikeres volt.'
  ELEMENTDATA: StudyDetailDto[] = [];
  displayedColumns: string[] = ['id', 'cim', 'actions']
  dataSource = new MatTableDataSource<StudyDetailDto>(this.ELEMENTDATA)
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: StudyService,
    private route: Router) {}

  ngOnInit(): void {
    this.refreshAll()
  }

  refreshAll() {
    this.service.getAll().subscribe(
      response => {
        this.dataSource.data = response as StudyDetailDto[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  export(id: number) {
    this.service.export(id).subscribe(
      response => {
        let file = new Blob([response], {type: 'application/vnd.ms-excel'});
        var fileUrl = URL.createObjectURL(file)
        window.open(fileUrl)
      }
    );
    this.refreshAll();
  }

  navigateUj(id: number, isDisabled: boolean) {
    this.route.navigate(['study/new/', id, isDisabled]);
  }

  navigateToDokumentumokKezelese(id: number) {
    this.route.navigate(['dokumentumok/', id]);
  }
}

export class StudyDetailDto {
  id: number;
  cim: string;

  constructor(id: number, cim: string) {
    this.id = id;
    this.cim = cim;
  }
}
