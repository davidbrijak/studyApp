import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Szemely} from "../szemely.component";
import {SzemelyService} from "../../service/data/szemely.service";
import {SzerepkorServiceService} from "../../service/data/szerepkor-service.service";
import {Szerepkor} from "../../szerepkor-list/szerepkor-list.component";

@Component({
  selector: 'app-uj-szemely',
  templateUrl: './uj-szemely.component.html',
  styleUrls: ['./uj-szemely.component.css']
})
export class UjSzemelyComponent implements OnInit {

  szemely: Szemely;
  szerepkorList: Szerepkor[] = [];
  message: string;
  isSuccesSave: boolean;
  title = 'Új személy felvétele'
  selectedJogosultsag: Szerepkor[];
  error: string
  isSmthEmpty: boolean

  constructor(protected service: SzemelyService,  protected router: Router, protected szerepkorService: SzerepkorServiceService) {
  }

  ngOnInit(): void {
    this.refreshSzerepkorok()
    this.szemely = new Szemely(-1, '', '', '', '', '', [])
  }

  refreshSzerepkorok() {
    this.szerepkorService.getAll().subscribe(
      response => {
        this.szerepkorList = response as Szerepkor[];
      }
    );
  }

  handleSave(szemely: Szemely) {
    if (this.selectedJogosultsag == undefined || !szemely.nev.length || !szemely.email.length
        || !szemely.tajszam.length || !szemely.postaCim.length || !szemely.telefonSzam.length) {
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
    }
    szemely.szerepkorAzonositok = this.selectedJogosultsag.map(jog => jog.id)
    this.service.create(szemely).subscribe(
      () => {
        this.isSuccesSave = true;
        this.isSmthEmpty = true;
        this.error = 'A mentés sikeres volt.';
        this.router.navigate(['szemelyek/list'])
      }
    )
  }
}
