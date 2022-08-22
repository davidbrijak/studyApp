import { Component, OnInit } from '@angular/core';
import {SzervezetTipus} from "../../szervezet-tipus-list/szervezet-tipus-list.component";
import {SzervezetTipusService} from "../../service/data/szervezet-tipus.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NewSzervezetDto} from "../szervezet-list.component";
import {SzervezetService} from "../../service/data/szervezet.service";

@Component({
  selector: 'app-uj-szervezet',
  templateUrl: './uj-szervezet.component.html',
  styleUrls: ['./uj-szervezet.component.css']
})
export class UjSzervezetComponent implements OnInit {

  szervezetTipusList: SzervezetTipus[] = [];
  selectedSzervezetTipus: SzervezetTipus;
  id: number;
  szervezet: NewSzervezetDto;
  message: string;
  isSuccesSave: boolean;
  title = 'Szervezet felvétele'
  error: string
  isSmthEmpty: boolean

  constructor(
    private szervezetTipusService: SzervezetTipusService,
    private szervezetService: SzervezetService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }


  ngOnInit(): void {
    this.refreshSzervezetTipus()
    this.id = this.route.snapshot.params['id'];
    if(this.id != -1) {
      this.title = 'Szervezet szerkesztése'
    }
    this.szervezet = new NewSzervezetDto(this.id, '', '', '', 0)
  }

  refreshSzervezetTipus() {
    this.szervezetTipusService.getAll().subscribe(
      response => {
        this.szervezetTipusList = response as SzervezetTipus[];
      }
    );
  }

  handleSave(newSzervezetDto: NewSzervezetDto) {
    if (!this.selectedSzervezetTipus || !newSzervezetDto.cim.length ||
    !newSzervezetDto.nev.length || !newSzervezetDto.adoszam.length) {
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
      return
    }
    newSzervezetDto.szervezetTipusAzonosito = this.selectedSzervezetTipus.id
    this.szervezetService.create(newSzervezetDto).subscribe(
      () => {
        this.message = "A mentés sikeres volt."
        this.isSuccesSave = true;
        this.router.navigate(['szervezetTipus/new/' + newSzervezetDto.szervezetTipusAzonosito + '/true' ]);
      }
    )
  }
}
