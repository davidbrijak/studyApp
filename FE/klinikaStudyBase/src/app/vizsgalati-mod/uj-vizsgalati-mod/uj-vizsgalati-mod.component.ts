import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {VizsgalatiModService} from "../../service/data/vizsgalati-mod/vizsgalati-mod.service";
import {VizsgalatiModDetailDto} from "../../dto/vizsgalatiModDetailDto";

@Component({
  selector: 'app-uj-vizsgalati-mod',
  templateUrl: './uj-vizsgalati-mod.component.html',
  styleUrls: ['./uj-vizsgalati-mod.component.css']
})
export class UjVizsgalatiModComponent implements OnInit {


  id: number;
  vm: VizsgalatiModDetailDto;
  message: string;
  isSuccesSave: boolean;
  title = 'Új vizsgálati mód';
  isDisabled: boolean;
  error: string
  isSmthEmpty: boolean

  constructor(protected service: VizsgalatiModService,  protected router: Router,
              protected activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.vm = new VizsgalatiModDetailDto(this.id, '', '', '','')
    this.isDisabled = this.activatedRoute.snapshot.params['isDisabled'];
    if (this.id != -1) {
      this.title = 'Vizsgálati mód szerkesztése'
      /*this.service.get(this.id).subscribe(
        data => this.fizetestiPeriodus = data
      )*/
    }
  }

  handleSave(vm: VizsgalatiModDetailDto) {
    if(!vm.eur || !vm.huf || !vm.megnevezes || !vm.hivatkozasiNev) {
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
      return
    }
    this.service.create(vm).subscribe(
      () => {
        this.message = "A mentés sikeres volt."
        this.isSuccesSave = true;
        this.router.navigate(['vizsgalatiMod/list']);
      }
    )
  }

}
