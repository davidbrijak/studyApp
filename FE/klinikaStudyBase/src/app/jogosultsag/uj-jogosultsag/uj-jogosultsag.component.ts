import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {JogosutsagDetailDto} from "../jogosultsag-list.component";
import {JogosultsagService} from "../../service/data/jogosultsag.service";

@Component({
  selector: 'app-uj-jogosultsag',
  templateUrl: './uj-jogosultsag.component.html',
  styleUrls: ['./uj-jogosultsag.component.css']
})
export class UjJogosultsagComponent implements OnInit {

  id: number;
  jogosultsag: JogosutsagDetailDto;
  message: string;
  isSuccesSave: boolean;
  title = 'Jogosultság felvétele'

  constructor(private service: JogosultsagService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.jogosultsag = new JogosutsagDetailDto(this.id, '')
    if (this.id != -1) {
      this.title = 'Jogosultság szerkesztése.'
      this.service.get(this.id).subscribe(
        data => this.jogosultsag = data
      )
    }
  }

  handleSave(jogosultsag: JogosutsagDetailDto) {
    this.service.create(jogosultsag).subscribe(
      response => {
        this.message = "A mentés sikeres volt."
        this.isSuccesSave = true;
        this.router.navigate(['jogosultsag/list']);
      }
    )
  }

}
