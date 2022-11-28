import {Component, OnInit} from '@angular/core';
import {SzerepkorServiceService} from "../../service/data/szerepkor-service.service";
import {Szerepkor} from "../szerepkor-list.component";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-uj-szerepkor',
  templateUrl: './uj-szerepkor.component.html',
  styleUrls: ['./uj-szerepkor.component.css']
})
export class UjSzerepkorComponent implements OnInit {

  id: number;
  szerepkor: Szerepkor;
  message: string;
  isSuccesSave: boolean;
  title = 'Szerepkör felvétele'
  isDisabled: boolean;
  error: string
  isSmthEmpty: boolean

  constructor(private service: SzerepkorServiceService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.isDisabled = this.route.snapshot.params['isDisabled'];
    this.szerepkor = new Szerepkor(this.id, '', '', 0)
    if (this.id != -1) {
      this.title = 'Szerepkör szerkesztése'
      this.service.get(this.id).subscribe(
        data => this.szerepkor = data
      )
    }
  }

  handleSave(szerep: Szerepkor) {
    if (!szerep.megnevezes.length || !szerep.hivatkozasiNev.length || szerep.kifizetesAranya === null) {
      this.isSmthEmpty = true;
      this.error = 'Kérem töltse ki az összes kötelező mezőt.';
      return
    }
    this.service.create(szerep).subscribe(
      () => {
        this.message = "A mentés sikeres volt."
        this.isSuccesSave = true;
        this.router.navigate(['szerepkor/list']);
      }
    )
  }
}
