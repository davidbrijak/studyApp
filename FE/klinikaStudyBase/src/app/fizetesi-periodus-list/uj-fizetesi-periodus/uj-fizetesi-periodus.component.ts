import {Component, OnInit} from '@angular/core';
import {FizetesiPeriodus, FizetesiPeriodusListComponent} from "../fizetesi-periodus-list.component";
import {ActivatedRoute, Router} from "@angular/router";
import {FizetesiPeriodusService} from "../../service/data/fizetesi-periodus.service";

@Component({
  selector: 'app-uj-fizetesi-periodus',
  templateUrl: './uj-fizetesi-periodus.component.html',
  styleUrls: ['./uj-fizetesi-periodus.component.css']
})
export class UjFizetesiPeriodusComponent extends FizetesiPeriodusListComponent implements OnInit {

  id: number;
  fizetestiPeriodus: FizetesiPeriodus;
  message: string;
  isSuccesSave: boolean;
  title = 'Új fizetési periódus';
  isDisabled: boolean;
  error: string
  isSmthEmpty: boolean

  constructor(protected service: FizetesiPeriodusService,  protected router: Router, protected activatedRoute: ActivatedRoute,) {
    super(service, router)
  }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.fizetestiPeriodus = new FizetesiPeriodus(this.id, '', '', 0)
    this.isDisabled = this.activatedRoute.snapshot.params['isDisabled'];
    if (this.id != -1) {
      this.title = 'Fizetési periódus szerkesztése'
      this.service.get(this.id).subscribe(
        data => this.fizetestiPeriodus = data
      )
    }
  }

  handleSave(fizetesiPeriodus: FizetesiPeriodus) {
    if (!fizetesiPeriodus.megnevezes.length || !this.fizetestiPeriodus.hivatkozasiNev.length || this.fizetestiPeriodus.napok === null) {
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
      return
    }
    this.service.create(fizetesiPeriodus).subscribe(
      () => {
        this.message = "A mentés sikeres volt."
        this.isSuccesSave = true;
        this.router.navigate(['fizetesiPeriodus/list']);
      }
    )
  }

}
