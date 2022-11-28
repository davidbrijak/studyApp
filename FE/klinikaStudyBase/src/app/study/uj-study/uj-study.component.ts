import {Component, OnInit, ViewChild} from '@angular/core';
import {SzervezetTipus} from "../../szervezet-tipus-list/szervezet-tipus-list.component";
import {Szervezet} from "../../szervezet-list/szervezet-list.component";
import {SzervezetTipusService} from "../../service/data/szervezet-tipus.service";
import {SzervezetService} from "../../service/data/szervezet.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FizetesiPeriodus} from "../../fizetesi-periodus-list/fizetesi-periodus-list.component";
import {FizetesiPeriodusService} from "../../service/data/fizetesi-periodus.service";
import {SzemelyService} from "../../service/data/szemely.service";
import {StudyService} from "../../service/data/study.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-uj-study',
  templateUrl: './uj-study.component.html',
  styleUrls: ['./uj-study.component.css']
})
export class UjStudyComponent implements OnInit {

  szerkText = 'Szerkesztés feloldása';
  isEditable = true
  isDisabled: true;
  szerzodesKotesDatuma: Date;
  szervezetTipusList: SzervezetTipus[] = [];
  szervezetList: Szervezet[] = [];
  fizetesiPeriodusList: FizetesiPeriodus[] = [];
  szemelyList: Szemely[] = [];
  selectedMegbizoCeg: Szervezet;
  selectedCro: Szervezet;
  id: number;
  cim: string;
  selectedKutatasVezeto: Szemely;
  selectedReszleg: Szervezet;
  selectedMonitor: Szemely;
  selectedPenzugyiKontakt: Szemely;
  selectedFizetesiPeriodus: FizetesiPeriodus;
  study: NewStudyDto;
  message: string;
  isSuccesSave: boolean;
  title = 'Study felvétele'
  isFizetve = 'Nem'
  ELEMENTDATA: MeresDetailDto[] = [];
  dataSource = new MatTableDataSource<MeresDetailDto>(this.ELEMENTDATA)
  displayedColumns: string[] = ['id', 'feltoltesSzam', 'alanyNev', 'vizsgaloOrvosNev', 'isFizetve', 'datum',
    'operator1IdNev', 'operator2IdNev', 'vizsgalatAra', 'actions']
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  error: string
  isSmthEmpty: boolean

  constructor(
    private szervezetTipusService: SzervezetTipusService,
    private szervezetService: SzervezetService,
    private router: Router,
    private route: ActivatedRoute,
    private  fizetesiPeriodusService: FizetesiPeriodusService,
    private szemelyService: SzemelyService,
    private studyService: StudyService
  ) {
  }

  ngOnInit(): void {
    this.refreshSzervezetTipus()
    this.refreshSzervezet()
    this.refreshFizetesiPeriodus()
    this.refreshSzemelyek()
    this.id = this.route.snapshot.params['id'];
    this.refreshMeresek()
    if(this.id != -1) {
      this.isEditable = false
      this.title = 'Study szerkesztése'
      this.studyService.get(this.id).subscribe(
        data => {
          this.study = data
         }
      )
    }
    this.study = new NewStudyDto(this.id, '', '', new Date(), undefined, undefined, undefined,
      undefined, undefined, undefined)
  }

  refreshMeresek(){
    this.studyService.getMeresekByStudy(this.id).subscribe(
      response => {
        this.dataSource.data = response as MeresDetailDto[];
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator
      }
    );
  }

  refreshSzervezetTipus() {
    this.szervezetTipusService.getAll().subscribe(
      response => {
        this.szervezetTipusList = response as SzervezetTipus[];
      }
    );
  }

  refreshSzervezet() {
    this.szervezetService.getAll().subscribe(
      response => {
        this.szervezetList = response as Szervezet[];
      }
    );
  }

  refreshFizetesiPeriodus() {
    this.fizetesiPeriodusService.getAll().subscribe(
      response => {
        this.fizetesiPeriodusList = response as FizetesiPeriodus[];
      }
    );
  }

  refreshSzemelyek() {
    this.szemelyService.getAll().subscribe(
      response => {
        this.szemelyList = response as Szemely[];
      }
    );
  }

  handleSave(newStudyDto: NewStudyDto) {
    if (newStudyDto.id == -1 && (!newStudyDto.studyKod || !newStudyDto.cim || !this.selectedMegbizoCeg || !this.selectedCro
     || !this.selectedKutatasVezeto || !this.selectedReszleg || !this.selectedMonitor || !this.selectedPenzugyiKontakt
    || !this.selectedFizetesiPeriodus || !this.szerzodesKotesDatuma)) {
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
      return
    }
    newStudyDto.cro = this.selectedCro === undefined ? this.study.cro : this.selectedCro
    newStudyDto.megbizoCeg = this.selectedMegbizoCeg === undefined ? this.study.megbizoCeg : this.selectedMegbizoCeg
    newStudyDto.kutatasVezeto = this.selectedKutatasVezeto === undefined ? this.study.kutatasVezeto : this.selectedKutatasVezeto
    newStudyDto.reszleg = this.selectedReszleg === undefined ? this.study.reszleg : this.selectedReszleg
    newStudyDto.monitor = this.selectedMonitor === undefined ? this.study.monitor : this.selectedMonitor
    newStudyDto.penzugyiKontakt = this.selectedPenzugyiKontakt === undefined ? this.study.penzugyiKontakt : this.selectedPenzugyiKontakt
    newStudyDto.fizetesiPeriodusDetailDto = this.selectedFizetesiPeriodus === undefined ? this.study.fizetesiPeriodusDetailDto : this.selectedFizetesiPeriodus
    newStudyDto.szerzodesKotesDatuma = this.szerzodesKotesDatuma === undefined ? this.study.szerzodesKotesDatuma : this.szerzodesKotesDatuma
    this.studyService.create(newStudyDto).subscribe(
      () => {
        this.message = "A mentés sikeres volt."
        this.isSuccesSave = true;
      }
    )
    this.refreshSzervezet()
    this.router.navigate(['study/list']);
  }

  navigateUjMeres(studyId:number, id: number, isDisabled: boolean) {
    this.router.navigate(['ujMeres/new/', studyId, id, isDisabled]);
  }

  makeEditable() {
    this.isEditable = !this.isEditable
    this.szerkText = this.isEditable ? 'Szerkesztés zárolása' : 'Szerkesztés feloldása';
  }

  navigateUj(studyId: number, id: number, isDisabled: boolean) {
    this.router.navigate(['ujMeres/new/', studyId, id, isDisabled]);
  }
}

export class Szemely {
  id: number;
  nev: string;

  constructor(id: number, nev: string) {
    this.id = id;
    this.nev = nev;
  }
}

export class NewStudyDto {
  id: number;
  cim: string;
  studyKod: string;
  megbizoCeg: Szervezet | undefined
  cro: Szervezet | undefined
  kutatasVezeto: Szemely | undefined;
  fizetesiPeriodusDetailDto: FizetesiPeriodus | undefined;
  szerzodesKotesDatuma: Date;
  penzugyiKontakt: Szemely | undefined;
  reszleg: Szervezet | undefined;
  monitor: Szemely | undefined

  constructor(
    id: number, cim: string, studyKod: string, szerzodesKotesDatuma: Date, megbizoCeg: Szervezet | undefined,
    cro: Szervezet | undefined, kutatasVezeto: Szemely | undefined, fizetesiPeriodusDetailDto: FizetesiPeriodus
      | undefined, reszleg: Szervezet | undefined, monitor: Szemely | undefined) {
    this.id = id;
    this.cim = cim;
    this.studyKod = studyKod;
    this.szerzodesKotesDatuma = szerzodesKotesDatuma;
    this.megbizoCeg = megbizoCeg;
    this.cro = cro;
    this.kutatasVezeto = kutatasVezeto;
    this.fizetesiPeriodusDetailDto = fizetesiPeriodusDetailDto;
    this.reszleg = reszleg
    this.monitor = monitor
  }
}

export class MeresDetailDto {
  id: number;
  feltoltesSzam: string;
  alanyNev: string;
  taj: string;
  vizsgaloOrvosNev: string;
  operator1IdNev: string;
  operator2IdNev: string;
  megjegyzes: string;
  datum: Date;
  vizsgalatAra: number;
  isFizetve: boolean
  vizsgalatiMod:string

  constructor(id: number, feltoltesSzam: string, alanyNev: string, taj: string, vizsgaloOrvosNev: string,
              operator1IdNev: string, operator2IdNev: string, megjegyzes: string, datum: Date, vizsgalatAra: number,
              fizetve: boolean, vizsgalatiMod: string) {
    this.id = id;
    this.feltoltesSzam = feltoltesSzam;
    this.alanyNev = alanyNev;
    this.taj = taj;
    this.vizsgaloOrvosNev = vizsgaloOrvosNev;
    this.operator1IdNev = operator1IdNev;
    this.operator2IdNev = operator2IdNev;
    this.megjegyzes = megjegyzes;
    this.datum = datum;
    this.vizsgalatAra = vizsgalatAra;
    this.isFizetve = fizetve;
    this.vizsgalatiMod = vizsgalatiMod
  }
}
