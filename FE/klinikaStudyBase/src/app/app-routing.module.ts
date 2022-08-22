import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {WelcomeComponent} from "./welcome/welcome.component";
import {ErrorComponent} from "./error/error.component";
import {SzerepkorListComponent} from "./szerepkor-list/szerepkor-list.component";
import {UjSzerepkorComponent} from "./szerepkor-list/uj-szerepkor/uj-szerepkor.component";
import {FizetesiPeriodusListComponent} from "./fizetesi-periodus-list/fizetesi-periodus-list.component";
import {UjFizetesiPeriodusComponent} from "./fizetesi-periodus-list/uj-fizetesi-periodus/uj-fizetesi-periodus.component";
import {SzervezetListComponent} from "./szervezet-list/szervezet-list.component";
import {SzervezetTipusListComponent} from "./szervezet-tipus-list/szervezet-tipus-list.component";
import {UjSzervezetTipusComponent} from "./szervezet-tipus-list/uj-szervezet-tipus/uj-szervezet-tipus.component";
import {UjSzervezetComponent} from "./szervezet-list/uj-szervezet/uj-szervezet.component";
import {StudyComponent} from "./study/study.component";
import {JogosultsagListComponent} from "./jogosultsag/jogosultsag-list.component";
import {UjJogosultsagComponent} from "./jogosultsag/uj-jogosultsag/uj-jogosultsag.component";
import {VizsgalatiModComponent} from "./vizsgalati-mod/vizsgalati-mod.component";
import {UjStudyComponent} from "./study/uj-study/uj-study.component";
import {UjMeresComponent} from "./meres/uj-meres/uj-meres.component";
import {UjSzemelyComponent} from "./szemely/uj-szemely/uj-szemely.component";
import {UjVizsgalatiModComponent} from "./vizsgalati-mod/uj-vizsgalati-mod/uj-vizsgalati-mod.component";
import {KifizetesekComponent} from "./kifizetesek/kifizetesek.component";
import {KifizetesDetailComponent} from "./kifizetesek/kifizetes-detail/kifizetes-detail.component";
import {DokumentumokKezeleseComponent} from "./dokumentumok-kezelese/dokumentumok-kezelese.component";
import {FileUploadComponentComponent} from "./file-upload-component/file-upload-component.component";
import {SzemelyekListComponent} from "./szemelyek-list/szemelyek-list.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'welcome/:name', component: WelcomeComponent},
  {path: '', component: LoginComponent},
  {path: 'szerepkor/list', component: SzerepkorListComponent},
  {path: 'szemely/new', component: UjSzemelyComponent},
  {path: 'fizetesiPeriodus/list', component: FizetesiPeriodusListComponent},
  {path: 'szervezet/list', component: SzervezetListComponent},
  {path: 'szervezetTipus/list', component: SzervezetTipusListComponent},
  {path: 'kifizetesek/list', component: KifizetesekComponent},
  {path: 'szemelyek/list', component: SzemelyekListComponent},
  {path: 'jogosultsag/list', component: JogosultsagListComponent},
  {path: 'study/list', component: StudyComponent},
  {path: 'vizsgalatiMod/list', component: VizsgalatiModComponent},
  {path: 'szerepkor/new/:id/:isDisabled', component: UjSzerepkorComponent},
  {path: 'kif/detail/:id', component: KifizetesDetailComponent},
  {path: 'study/new/:id/:isDisabled', component: UjStudyComponent},
  {path: 'szervezetTipus/new/:id/:isDisabled', component: UjSzervezetTipusComponent},
  {path: 'ujMeres/new/:studyId/:id/:isDisabled', component: UjMeresComponent},
  {path: 'szervezet/new/:id', component: UjSzervezetComponent},
  {path: 'vm/new/:id', component: UjVizsgalatiModComponent},
  {path: 'fizetesiPeriodus/new/:id/:isDisabled', component: UjFizetesiPeriodusComponent},
  {path: 'jogosultsag/new/:id', component: UjJogosultsagComponent},
  {path: 'dokumentumok/:id', component: FileUploadComponentComponent},
  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
